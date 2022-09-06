package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.model.Role;
import com.example.moonlighthotel.repositories.RoleRepository;
import com.example.moonlighthotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.example.moonlighthotel.constant.ExceptionConstant.ROLE_NOT_FOUND;

@Service
public class RoleServiceImpl implements RoleService {

    public RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
       roleRepository.save(role);
    }

    @Override
    public Role findRoleByAuthority(String authority) {
        return roleRepository.findByAuthority(authority)
                .orElseThrow(() -> new NoSuchElementException(String.format(ROLE_NOT_FOUND, authority)));
    }
}

