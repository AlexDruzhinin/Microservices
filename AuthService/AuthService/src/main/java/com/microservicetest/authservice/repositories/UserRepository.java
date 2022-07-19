package com.microservicetest.authservice.repositories;

import com.microservicetest.authservice.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String userName);
}
