package com.epam.learn.service;

import com.epam.learn.model.UserAccount;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAccountServiceClient {

    private final RestTemplate restTemplate;

    public UserAccountServiceClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public UserAccount getUserDetails(Long id) {
        return restTemplate.getForObject("/user/{id}",
                UserAccount.class, id);
    }
}

