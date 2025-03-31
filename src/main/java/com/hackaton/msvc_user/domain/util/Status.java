package com.hackaton.msvc_user.domain.util;

public enum Status {
    ACTIVE("Activo"),
    TRANSFERRED("Trasladado"),
    RETIRED("Retirado");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

}