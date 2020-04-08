package com.epam.learn.controller;

/*
SPARING COURSE 57
Question 1
*/
/*
netstat -ao |find /i "listening"
Taskkill /F /IM 36836
*/
//The following request has been submitted:
//[POST] http://xxxx/person/add?name=John
//[POST] http://localhost:8084/person/add?name=John

//curl -d "{"""name""":"""John""","""id""":"""12"""}" -H "Content-Type: application/json" -X POST http://localhost:8084/person/add?id=12&name=John
//curl -X POST http://localhost:8084/person/add?name=John


//33333333333333
//[GET] http://localhost:8084/person/edit?id=12
import com.epam.learn.model.Person;
import com.epam.learn.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
@Controller
@RequestMapping(value = "person")
public class PersonController3 {

    @Autowired
//    private PersonService personService;
    private UsersService usersService;

    @RequestMapping(value = {"/add", "/edit"}, method = {RequestMethod.POST})
    public String addPerson(@ModelAttribute("person") Person person) {

        System.out.println("33333333333333333 add/edit/POST !!!!!!! STORED !!!!!!!!!!  ");
//        personService.save(person);
        return "redirect:view?id=" + person.getId();
    }


    @ModelAttribute("person")
    public Person getPerson(@RequestParam(value = "id", required = false) Integer id) {
        System.out.println("33333333333333333    @ModelAttribute(\"person\")  ");

        Person person = null;
        if (id != null) {
//            person = personService.findOne(id);
            person = new Person();
            person.setId(String.valueOf(id));
        }
        return person == null ? new Person() : person;
    }
//
//@RequestMapping(value="/person/add", method=RequestMethod.POST)
//@RequestMapping(value="/person/add",method=RequestMethod.POST,params="!id, !name")

//+++++++++++++++++++++++++++++++++++++++++++++++++++
// IF in BROWSER + GET!!!!!!!!!!!!!!!!!!!!!!
//@RequestMapping(value="/person/add", method=RequestMethod.GET)
//+++++++++++++++++++++++++++++++++++++++++++++++++++


//@RequestMapping(value="/add", method=RequestMethod.POST)


  ///2


//@RequestMapping(value="/person/add",method=RequestMethod.POST, params="id")
//
    //THIS ALSO WORKS FOR CONTROLLER
//@RequestMapping(value="/person/add",method=RequestMethod.POST, params="!id")
//

//@RequestMapping(value="/person/add",method=RequestMethod.GET, params="id")
//

////WORKS!!!!!!!!!!!!!!!!!!!!!!
@RequestMapping(value="/add",method=RequestMethod.POST, params="!id")
////WORKS!!!!!!!!!!!!!!!!!!!!!!

    public String addPerson(@RequestParam("name") String name) {

      System.out.println("+++3333333333333333 @RequestMapping(value=\"/person/add\", method=RequestMethod.POST)");
      System.out.println("name = " + name);

      Person person = new Person();
      person.setName(name);

      System.out.println("!!!!!!! STORED !!!!!!!!!!  ");
//      personService.save(person);
      return "redirect:/view?id=" + person.getId();
  }


    public String addPerson(@RequestParam("id") String id,
                            @RequestParam("name") String name) {

        System.out.println("33333333333333333    addPerson  ");

        Person person = new Person();
        person.setId(id);
        person.setName(name);


//        personService.save(person);

        return "redirect:/view?id=" + person.getId();
    }

//    @RequestMapping(value = "view", method = RequestMethod.GET)
//    public String viewPersonForm(@RequestParam("id") String id) {
//        System.out.println("++222222222222@RequestMapping(value = \"view\", method = RequestMethod.GET)");
//
//    return "person/view";
//    }
@RequestMapping(value = "/view", method = RequestMethod.GET)
public String viewPersonForm() {

    System.out.println("++3333333333333@RequestMapping(value = \"view\", method = RequestMethod.GET)");

    return "person/view";
}

    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    public String showEditForm() {
        System.out.println("++3333333333333value = \"/edit\", method = {RequestMethod.GET}");
        return "/person/edit";
    }
///////////////////







}