package com.mircoservicetest.historicalservice.dao;

import com.mircoservicetest.historicalservice.model.Rate;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RateRepository extends CrudRepository<Rate, Long> {

    public List<Rate> findFirstByNameOrderByTradeTimeDesc(String name);

    public List<Rate> findByNameAndTradeTimeBetween(String name, LocalDateTime start, LocalDateTime stop);
}
