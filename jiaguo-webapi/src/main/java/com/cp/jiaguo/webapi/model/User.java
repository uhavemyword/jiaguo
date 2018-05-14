package com.cp.jiaguo.webapi.model;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity {

    private String name;

    private String email;

    private List<Contact> contacts;

    public User() {
        this.contacts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
