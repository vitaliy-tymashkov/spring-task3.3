package com.epam.learn.controller;

/*
SPARING COURSE 57
Question 1
*/
/*
netstat -ao |find /i "listening"
Taskkill /F /IM 36836
*/
//http://localhost:8084/person/add?id=12&name=John

//curl -d "{"""name""":"""John""","""id""":"""12"""}" -H "Content-Type: application/json" -X POST http://localhost:8084/person/add?id=12&name=John

import com.epam.learn.model.Person;
import com.epam.learn.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
//@Controller @RequestMapping(value = "person")
public class PersonController {

    @Autowired
//    private PersonService personService;
    private UsersService usersService;

//
  @RequestMapping(value="/person/add", method=RequestMethod.POST)
//@RequestMapping(value="/person/add",method=RequestMethod.POST,params="!id, !name")

//+++++++++++++++++++++++++++++++++++++++++++++++++++
// IF in BROWSER + GET!!!!!!!!!!!!!!!!!!!!!!
//@RequestMapping(value="/person/add", method=RequestMethod.GET)
//+++++++++++++++++++++++++++++++++++++++++++++++++++


//@RequestMapping(value="/add", method=RequestMethod.POST)
    public String addPerson(@RequestParam("id") String id,
                            @RequestParam("name") String name) {

        System.out.println("+++ @RequestMapping(value=\"/person/add\", method=RequestMethod.POST)");
        System.out.println("id = " + id);
        System.out.println("name = " + name);

        Person person = new Person();
        person.setId(id);
        person.setName(name);

        System.out.println("!!!!!!! STORED !!!!!!!!!!  ");
//        personService.save(person);

        return "redirect:/view?id=" + person.getId();
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String viewPersonForm(@RequestParam("id") String id) {
//        ...
        System.out.println("++@RequestMapping(value = \"view\", method = RequestMethod.GET)");

        return "person/view";
    }
}