package com.example.authservice;

import com.example.authservice.dao.UsersRepository;
import com.example.authservice.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class AuthServiceApplication {
    public static void main(String[] args) {SpringApplication.run(AuthServiceApplication.class, args);}
}
