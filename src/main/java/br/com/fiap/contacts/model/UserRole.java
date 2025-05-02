package br.com.fiap.contacts.model;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    private String getRole() {
        return role;
    }

    }
