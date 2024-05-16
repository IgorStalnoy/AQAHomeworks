package com.aqa.homeworks.buisness;

import lombok.Getter;

@Getter
public class User {
    private int id;
    private final String name;
    private final String address;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public User(int id,String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "id : " + id + " | name : " + name + " | address : " + address;
    }
}
