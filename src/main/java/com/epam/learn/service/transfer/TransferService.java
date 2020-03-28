package com.epam.learn.service.transfer;

import com.epam.learn.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransferService {

    @Autowired
    private IUsersService usersService;

    public Boolean transferMoney(int idFrom, int idTo, int amount) {

//        usersService.transferMoney(idFrom, idTo, amount);

        return Boolean.TRUE;
    }

    public Boolean deductFee(int idFrom, int amount) {

//        usersService.deductFee(idFrom, amount);

        return Boolean.TRUE;
    }
}
