package com.example.misc.entry;
//用户类
public class User {
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String id;
    private String role;

    public User() {

    }

    public User(String name, String password, String email, String phone, String address, String id, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.id = id;
        this.role = role;
    }

}
