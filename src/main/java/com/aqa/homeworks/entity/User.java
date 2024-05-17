package com.aqa.homeworks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private int id;
    private final String name;
    private final String address;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "id : " + id + " | name : " + name + " | address : " + address;
    }
}
