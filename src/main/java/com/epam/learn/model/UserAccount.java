package com.epam.learn.model;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "userCNVR")
public class UserAccount {

    private Long id;
    private String name;
    private String phoneNumber;
    private String phoneOperator;
    private int balance;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @XmlElement
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneOperator() {
        return phoneOperator;
    }

    @XmlElement
    public void setPhoneOperator(String phoneOperator) {
        this.phoneOperator = phoneOperator;
    }

    public int getBalance() {
        return this.balance;
    }

    @XmlElement
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
