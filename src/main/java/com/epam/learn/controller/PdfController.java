package com.epam.learn.controller;

import com.epam.learn.model.UserAccount;
import com.epam.learn.service.GeneratePdfReport;
import com.epam.learn.service.UsersService;
import com.epam.learn.view.UserPDFView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Controller
@RequestMapping("/api/pdf")
public class PdfController extends AbstractController {

    @Autowired
    private UsersService usersService;


    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> downloadPDFFile() {

        List<UserAccount> userAccount = this.usersService.findAll();

        ByteArrayInputStream bis = GeneratePdfReport.createReport(userAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Task4.pdf");


        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @Bean
    @Override
    @RequestMapping(value = "/user_pdf", method = RequestMethod.GET, headers = "Accept=application/pdf")
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        List<UserAccount> userAccountList = usersService.findAll();

        Map<String,String> userData = new HashMap<String,String>();

        for (UserAccount userAccount : userAccountList){
            userData.put(userAccount.getId().toString(), userAccount.getName());
        }

        return new ModelAndView(new UserPDFView(),"userData",userData);
    }
}