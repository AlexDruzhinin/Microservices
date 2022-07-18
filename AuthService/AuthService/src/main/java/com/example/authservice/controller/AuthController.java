package com.example.authservice.controller;

import com.example.authservice.dao.UsersRepository;
import com.example.authservice.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {
    private final UsersRepository usersRepository;

    @Autowired
    public AuthController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/addUser")
    public void addUser(@NonNull @RequestParam String login, @NonNull @RequestParam String password) {
        Users user = new Users(login, password);

        usersRepository.save(user);
        System.out.println("Successfully Saved");

        List<Users> users = usersRepository.findByUserName(login);

        if (users != null & users.size() > 0) {
            Users user1 = users.get(0);
            System.out.println("Received User:" + user1.getUserName());
        }
    }
}
