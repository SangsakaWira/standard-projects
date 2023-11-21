package com.wira.sasangka.standardprojects.entity;

public enum ERole {
    USER("user"),
    ADMIN("admin");

    final String name;

    ERole(String name) {
        this.name = name;
    }

    public ERole valuesOf(String name) {
        for (ERole role : ERole.values()) {
            if (role.name.equals(name)) {
                return role;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return this.name();
    }
}
