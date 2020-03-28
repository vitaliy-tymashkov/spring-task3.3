package com.epam.learn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    private Long id;
    private String name;
    private String phoneNumber;
    private String phoneOperator;
    private int balance;

}
