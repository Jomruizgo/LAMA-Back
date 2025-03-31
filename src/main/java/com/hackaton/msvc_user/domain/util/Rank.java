package com.hackaton.msvc_user.domain.util;

public enum Rank {
    PRESIDENT("Presidente", true),
    VICE_PRESIDENT("Vicepresidente", true),
    TREASURER("Tesorero", true),
    BUSINESS_MANAGER("Gerente de Negocios", true),
    SECRETARY("Secretario", true),
    MTO("Oficial de Moto-Touring", true),
    FULL_COLOR("Full Color", false),
    ROCKETS("Rockets", false),
    PROSPECT("Prospecto", false);

    private final String rankName;
    private final boolean isAdmin;

    Rank(String rankName, boolean isAdmin) {
        this.rankName = rankName;
        this.isAdmin = isAdmin;
    }

    public String getRankName() {
        return rankName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public UserRole getRole() {
        return isAdmin ? UserRole.ADMIN : UserRole.CLIENT;
    }
}