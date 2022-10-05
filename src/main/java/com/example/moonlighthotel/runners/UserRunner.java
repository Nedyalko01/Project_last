package com.example.moonlighthotel.runners;

import com.example.moonlighthotel.repositories.UserRepository;
import com.example.moonlighthotel.service.RoleService;
import com.example.moonlighthotel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRunner implements CommandLineRunner {


    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;
    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserRunner(UserServiceImpl userServiceImpl, UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {

//        Role roleClient = new Role();
//        roleClient.setAuthority("ROLE_CLIENT");
//        roleService.save(roleClient);
//
//        Role roleAdmin = new Role();
//        roleAdmin.setAuthority("ROLE_ADMIN");
//        roleService.save(roleAdmin);
//
//        User firstUser = new User();
//        firstUser.setFirstName("Georgi");
//        firstUser.setLastName("Avramovt");
//        firstUser.setCreatedAt(Instant.now());
//        firstUser.setEmail("georgievr@gmail.com");
//        firstUser.setPhoneNumber("008796u76996");
//        firstUser.setRoles(Set.of(roleClient));
//        firstUser.setPassword(bCryptPasswordEncoder.encode("123456"));
//
//
//        userRepository.save(firstUser);
//
//        User secondUser = new User();
//        secondUser.setFirstName("Ivan");
//        secondUser.setLastName("Peev");
//        secondUser.setCreatedAt(Instant.now());
//        secondUser.setEmail("ivand@gmail.com");
//        secondUser.setPhoneNumber("008889669856");
//        secondUser.setPassword(bCryptPasswordEncoder.encode("321654"));
//        secondUser.setRoles(Set.of(roleAdmin));
//        userRepository.save(secondUser);

        // test for update method:

        //public User swap (Long id, User update) {
        //        User dbRole = findUserById(id);
        //        dbRole.setFirstName(update.getFirstName());
        //        return userRepository.save(dbRole);
        //    }

//        User updateFirstUser = new User ();
//
//        updateFirstUser.setFirstName("Anni");
//        userServiceImpl.swap(firstUser.getId(), secondUser);
//        //userServiceImpl.swap(firstUser.getId(), updateFirstUser);// (Id, updateFirstUser/secondUser)

    }

}
