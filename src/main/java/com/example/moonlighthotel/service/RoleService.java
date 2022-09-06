package com.example.moonlighthotel.service;

import com.example.moonlighthotel.model.Role;

public interface RoleService {

    void save(Role role);

    Role findRoleByAuthority(String roleAuthority);
}

