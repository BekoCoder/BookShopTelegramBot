package com.example.bookshopbot.enumeration;

public enum UserRole {
    USER("Oddiy user"),
    ADMIN("Admin"),
    SUPER_ADMIN("Super admin");

    UserRole(String name) {
        this.name = name;

    }
    public String name;
}
