package com.example.moonlighthotel.converter;

import com.example.moonlighthotel.model.Role;
import com.example.moonlighthotel.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleConverter {

    private static RoleServiceImpl roleService;

    @Autowired
    public RoleConverter(RoleServiceImpl roleService) {
        RoleConverter.roleService = roleService;
    }

    public static Set<Role> convertRoleStringToRole(Set<String> role) {

        String roleString = role.iterator().next();

        Role foundRole = roleService.findRoleByAuthority(RolePrefixConverter.addPrefix(roleString));

        Set<Role> roles = new HashSet<>();

        roles.add(foundRole);

        return roles;
    }
}
