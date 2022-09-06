package com.example.moonlighthotel.runners;

import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.repositories.UserRepository;
import com.example.moonlighthotel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserRunner implements CommandLineRunner {


    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserRunner(UserServiceImpl userServiceImpl, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        User firstUser = new User();
        firstUser.setFirstName("Georgi");
        firstUser.setLastName("Avramovt");
        firstUser.setCreatedAt(Instant.now());
        firstUser.setEmail("georgievr@gmail.com");
        firstUser.setPhoneNumber("008796u76996");
        firstUser.setPassword("123456");
        userRepository.save(firstUser);

        User secondUser = new User();
        secondUser.setFirstName("Ivan");
        secondUser.setLastName("Peev");
        firstUser.setCreatedAt(Instant.now());
        secondUser.setEmail("ivand@gmail.com");
        secondUser.setPhoneNumber("008889669856");
        secondUser.setPassword("321654");
        userRepository.save(secondUser);

        // test for update method:

        //public User swap (Long id, User update) {
        //        User dbRole = findUserById(id);
        //        dbRole.setFirstName(update.getFirstName());
        //        return userRepository.save(dbRole);
        //    }

        User updateFirstUser = new User ();

        updateFirstUser.setFirstName("Anni");
        userServiceImpl.swap(firstUser.getId(), secondUser);
        //userServiceImpl.swap(firstUser.getId(), updateFirstUser);// (Id, updateFirstUser/secondUser)

    }

}
