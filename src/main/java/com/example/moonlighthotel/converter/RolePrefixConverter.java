package com.example.moonlighthotel.converter;

import java.util.Set;

import static com.example.moonlighthotel.constant.UserConstant.ROLE_PREFIX;

public class RolePrefixConverter {

    public static Set<String> removePrefix(Set<String> role) {

        String roleString = role.iterator().next();
        return Set.of(roleString.substring(5).toLowerCase());
    }

    public static String addPrefix(String role) {

        return role.contains(ROLE_PREFIX) ? role.toUpperCase() : (ROLE_PREFIX + role.toUpperCase());
    }
}
