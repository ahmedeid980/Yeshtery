package com.ahmedeid.yeshtery.Yeshtery.util;

import lombok.Getter;

public enum UserRole {

    ADMIN(1, "Administrator"),
    USER(0, "User");

    @Getter
    private final int codeOfRole;
    @Getter
    private final String roleDescription;

    UserRole(final int codeOfRole, final String roleDescription) {
        this.codeOfRole = codeOfRole;
        this.roleDescription = roleDescription;
    }
}
