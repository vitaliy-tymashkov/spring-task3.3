package com.epam.learn.service;

import com.epam.learn.model.UserAccount;

import java.util.List;

public interface IUsersService {

    List<UserAccount> findAll();
    List<UserAccount> findUserByName(String name);
    String findUserNameById(String id);
    Integer findUserBalanceById(String id);
    List<UserAccount> findUserByNameExcludingId(String name, String id);

    void transferMoney(int idFrom, int idTo, int amount);
    void deductFee(int idFrom, int amount);
}

