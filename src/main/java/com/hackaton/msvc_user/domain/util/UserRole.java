package com.hackaton.msvc_user.domain.util;

public enum UserRole {
    ADMIN("ADMINISTRATOR"),
    CLIENT("MEMBER");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
