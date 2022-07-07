package com.mircoservicetest.historicalservice.dao;

import com.mircoservicetest.historicalservice.model.Rate;
import org.springframework.data.repository.CrudRepository;

public interface RateRepository extends CrudRepository<Rate, Long> {

    //List<Message> findByTag(String tag);{
}
