package com.compass.compass.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String zipCode;
    private String address;

    public User(String id, String name, String lastName, String email, String zipCode, String address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.zipCode = zipCode;
        this.address = address;
    }
}
