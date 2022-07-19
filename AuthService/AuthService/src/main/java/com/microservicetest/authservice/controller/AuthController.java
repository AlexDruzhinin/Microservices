package com.microservicetest.authservice.controller;

import com.microservicetest.authservice.entities.Role;
import com.microservicetest.authservice.repositories.RoleRepository;
import com.microservicetest.authservice.repositories.UserRepository;
import com.microservicetest.authservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }



    @GetMapping("/addUser")
    public void addUser(@NonNull @RequestParam String login,
                        @NonNull @RequestParam String password,
                        @NonNull @RequestParam String role) {
        //User user = new User();
        //user.setUserName(login);
        //user.setPassword(password);
        //user.getRoles().add(new Role(role));
        Role roleObj = roleRepository.findByName(role);
        if (roleObj != null)  {
            User user = new User();
            user.setUserName(login);
            user.setPassword(password);
            user.getRoles().add(roleObj);
            userRepository.save(user);
            System.out.println("Successfully Saved");
        }

    }

    @GetMapping("/")
    public String home() {
        return "Hello Guest";
    }

    @GetMapping("/secured")
    public String secured(Principal principal) {
        return "Hello " + principal.getName();
    }
}
