//package com.example.moonlighthotel.runners;
//
//import com.example.moonlighthotel.model.Role;
//import com.example.moonlighthotel.service.RoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RoleRunner implements CommandLineRunner {
//
//    private RoleService roleService;
//
//    @Autowired
//    public RoleRunner(RoleService roleService) {
//        this.roleService = roleService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Role roleClient = new Role();
//        roleClient.setAuthority("ROLE_CLIENT");
//        roleService.save(roleClient);
//
//        Role roleAdmin = new Role();
//        roleAdmin.setAuthority("ROLE_ADMIN");
//        roleService.save(roleAdmin);
//    }
//}
//
