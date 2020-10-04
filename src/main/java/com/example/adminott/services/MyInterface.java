package com.example.adminott.services;

public interface MyInterface {
    void register();

    void login(String name, String email, String created_at);

    void logout();

    void homepage();

}
