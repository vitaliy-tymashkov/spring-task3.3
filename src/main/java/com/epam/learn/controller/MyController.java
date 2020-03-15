package com.epam.learn.controller;

import com.epam.learn.model.City;
import com.epam.learn.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private ICityService cityService;

    @GetMapping(value = "/")
    public String index(Model model) {

        return "index";
    }

    @GetMapping(value="/cities")
    public ModelAndView showCities() {

        List<City> cities = cityService.findAll();

        HashMap<String, Object> params = new HashMap<>();
        params.put("cities", cities);

        return new ModelAndView("showCities", params);
    }
}
