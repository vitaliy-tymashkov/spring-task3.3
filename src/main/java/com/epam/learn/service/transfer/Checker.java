package com.epam.learn.service.transfer;

public class Checker {

    public Boolean isEnoughMoney(int balance, int TRANSFER_FEE){
            if (balance >= TRANSFER_FEE) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
    }
}
