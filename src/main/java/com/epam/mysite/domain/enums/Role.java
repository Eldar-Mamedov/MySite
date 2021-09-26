package com.epam.mysite.domain.enums;

public enum Role {
    Unauthorized("Unauthorized"), Client("Client"), Master("Master"), Admin("Admin");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
