package com.mircoservicetest.historicalservice.dao;

import com.mircoservicetest.historicalservice.dto.RawMoexRate;
import com.mircoservicetest.historicalservice.model.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RateRepository extends CrudRepository<Rate, Long> {

    //List<Message> findByTag(String tag);{

    //@Query("select u from User u where u.emailAddress = ?1")
    //User findByEmailAddress(String emailAddress);

    //@Query(value = "SELECT * FROM rate WHERE name = ?1 AND trade_time = (SELECT max (trade_time) FROM rate WHERE name = ?1) LIMIT 1", nativeQuery = true)
    //@Query(value = "SELECT * FROM rate WHERE id=556", nativeQuery = true)
    //public List<Rate> getLastRate(String name);


    public List<Rate> findFirstByNameOrderByTradeTimeDesc(String name);

    public List<Rate> findByNameAndTradeTimeBetween(String name, LocalDateTime start, LocalDateTime stop);
}
