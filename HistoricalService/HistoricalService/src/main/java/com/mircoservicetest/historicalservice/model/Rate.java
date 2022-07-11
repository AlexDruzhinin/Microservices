package com.mircoservicetest.historicalservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    String name;
    double price;
    LocalDateTime tradeTime;

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", dateTime=" + tradeTime +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return name.equals(rate.name) && tradeTime.equals(rate.tradeTime) && Double.compare(rate.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, tradeTime);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDateTime dateTime) {
        this.tradeTime = dateTime;
    }
}
