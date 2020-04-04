package com.epam.learn.controller;

import com.epam.learn.model.UserAccount;
import com.epam.learn.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("apiXML/userCNVR")
public class XMLController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value="name/{name}", method = RequestMethod.GET)
    public @ResponseBody UserAccount getUserInXML(@PathVariable String name) {
        return this.usersService.findOneUserByName(name);
    }

    @ResponseBody
    @RequestMapping(value="id/{id}", method=RequestMethod.GET)
    public UserAccount findUser(@PathVariable("id") String id){
        return this.usersService.findUserById(id);
    }

    @ResponseBody
    @RequestMapping(value="findAll", method=RequestMethod.GET)
    public List<UserAccount> findAll(){
        return this.usersService.findAll();
    }
}
