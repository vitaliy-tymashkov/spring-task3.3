package com.epam.learn.controller;

import com.epam.learn.model.UserAccount;
import com.epam.learn.service.IUsersService;
import com.epam.learn.service.transfer.Checker;
import com.epam.learn.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class MyController {
    private static final Integer TRANSFER_FEE = 100;//TODO Get the value from DB

    @Autowired
    private IUsersService usersService;

    @GetMapping(value = "/")
    public String index(Model model) {

        return "index";
    }

    @GetMapping(value="/users")
    public ModelAndView showUsers() {

        List<UserAccount> users = usersService.findAll();

        HashMap<String, Object> params = new HashMap<>();
        params.put("users", users);

        return new ModelAndView("showUsers", params);
    }
    @GetMapping(value="/editUser")
    public ModelAndView editUser(@RequestParam String userId) {

        UserAccount user = usersService.findUserById(userId);

        HashMap<String, Object> params = new HashMap<>();
        params.put("user", user);

        return new ModelAndView("editUser", params);
    }

    @PostMapping(value="/editUser")
    public ModelAndView editUserPost(@RequestParam String fid, @RequestParam String fname, @RequestParam String fnumber, @RequestParam String fcompany, @RequestParam String fbalance) {

        System.out.println("editUserPost = " + fid);
        UserAccount userAccount = new UserAccount();
        userAccount.setName(fname);
        userAccount.setPhoneNumber(fnumber);
        userAccount.setPhoneOperator(fcompany);
        userAccount.setBalance(Integer.valueOf(fbalance.replaceAll("[^\\d]", "")));


        usersService.updateUser(Integer.valueOf(fid), userAccount);

        List<UserAccount> users = usersService.findAll();

        HashMap<String, Object> params = new HashMap<>();
        params.put("users", users);

        return new ModelAndView("showUsers", params);
    }

    @GetMapping(value="/changeOperator")
    public ModelAndView changeOperator(@RequestParam String userId, String operator) {

        String name = usersService.findUserNameById(userId);
        Integer balance = usersService.findUserBalanceById(userId);
        String transferFeeMessage = "";
        Boolean operationApproved = new Checker().isEnoughMoney(balance, TRANSFER_FEE);
        if (operationApproved) {
            transferFeeMessage = "Transfer fee of " + TRANSFER_FEE + " will be debited.";
        } else {
            transferFeeMessage = "Not enough money to finish transfer (necessary more " + (TRANSFER_FEE - balance) + ").";
        }

        List<UserAccount> users = usersService.findUserByNameExcludingId(name, userId);

        HashMap<String, Object> params = new HashMap<>();
        params.put("users", users);
        params.put("userIdFrom", userId);
        params.put("userName", name);
        params.put("operator", operator);
        params.put("balance", balance);
        params.put("transferFeeMessage", transferFeeMessage);
        params.put("operationApproved", operationApproved);

        return new ModelAndView("changeOperator", params);
    }

    //changeMobileOperator
    @GetMapping(value="/updateBalance")
    public ModelAndView updateBalance(@RequestParam String from, String to, String operator) {

        String name = usersService.findUserNameById(from);
        Integer balance = usersService.findUserBalanceById(from);
        String transferFeeMessage = "";
        Boolean operationApproved = new Checker().isEnoughMoney(balance, TRANSFER_FEE);
        Boolean operationIsDone;

        int amount = balance;
        int idFromInt = Integer.parseInt(from);
        System.out.println("idFromInt = " + idFromInt);

        int idToInt = Integer.parseInt(to);
        System.out.println("idToInt = " + idToInt);


        if (operationApproved) {
            TransferService ts = new TransferService();

            System.out.println("TRANSFER_FEE = " + TRANSFER_FEE);
            usersService.deductFee(idFromInt, TRANSFER_FEE);

            usersService.transferMoney(idFromInt, idToInt, amount-TRANSFER_FEE);
            operationIsDone = Boolean.TRUE;

            transferFeeMessage = "Transfer accomplished!";
        } else {
            operationIsDone = Boolean.FALSE;
            transferFeeMessage = "Not enough money to finish transfer (necessary more " + (TRANSFER_FEE - balance) + ").";
        }

        List<UserAccount> users = usersService.findUserByName(name);

        HashMap<String, Object> params = new HashMap<>();
        params.put("users", users);
        params.put("userName", name);
        params.put("userIdFrom", idFromInt);
        params.put("to", idToInt);

        params.put("operator", operator);
        params.put("balance", amount-TRANSFER_FEE);
        params.put("transferFeeMessage", transferFeeMessage);
        params.put("operationApproved", operationApproved);
        params.put("operationIsDone", operationIsDone);

        return new ModelAndView("updateReport", params);
    }
}
