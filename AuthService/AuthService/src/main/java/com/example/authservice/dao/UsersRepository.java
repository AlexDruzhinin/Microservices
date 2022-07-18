package com.example.authservice.dao;

import com.example.authservice.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepository extends CrudRepository<Users, String> {

    public List<Users> findByUserName(String userName);
    //public List<Rate> findFirstByNameOrderByTradeTimeDesc(String name);
    //public List<Rate> findByNameAndTradeTimeBetween(String name, LocalDateTime start, LocalDateTime stop);
}
