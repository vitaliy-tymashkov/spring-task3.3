package com.epam.learn.controller;

import com.epam.learn.model.UserAccount;
import com.epam.learn.service.UsersService;
import com.epam.learn.util.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UsersService usersService;


    @ResponseBody
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public UserAccount findUser(@PathVariable("id") String id){
        return this.usersService.findUserById(id);
    }

    @ResponseBody
    @RequestMapping(value={"/all" , "/all/{order}","/all/{order}/{paginationFrom}","/all/{order}/{paginationFrom}/{paginationTo}"}, method=RequestMethod.GET)
    public List<UserAccount> findAllUsers(
            @PathVariable(name="order", required = false) String order,
            @PathVariable(name="paginationFrom", required = false) String paginationFrom,
            @PathVariable(name="paginationTo", required = false) String paginationTo){

        if (order != null) {
            if (order.equalsIgnoreCase("DESCENDING")) {
                return this.usersService.findAllEnhanced("DESC", paginationFrom, paginationTo);
            } else {
                return this.usersService.findAllEnhanced("ASC", paginationFrom, paginationTo);
            }
        }
        return this.usersService.findAllEnhanced("ASC", paginationFrom, paginationTo);
    }

    @ResponseBody
    @RequestMapping(value="/json/{id}}", method=RequestMethod.GET)
    public UserAccount findOneUserJson(@PathVariable(name="id") String id){
        System.out.println("findOneUserJson");
        return this.usersService.findUserById(id);
    }

    @PostMapping(path= "addUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserAccount> addUser(
            @RequestBody UserAccount userAccount
    )
            throws Exception
    {
        //Generate resource id (increment of current size)
        int id = usersService.findAll().size() + 1;
        userAccount.setId((long) id);

        //add resource
        usersService.addUser(userAccount);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/user/{id}")
                .buildAndExpand(userAccount.getId())
                .toUri();

        //Send location in response
        System.out.println("addUser = " + userAccount.toString());
        System.out.println("location = " + location.toString());
        System.out.println("ResponseEntity.created(location).build() = " + ResponseEntity.created(location).build().toString());
        return ResponseEntity.created(location).build();
    }
}