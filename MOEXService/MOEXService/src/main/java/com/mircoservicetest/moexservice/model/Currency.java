package com.mircoservicetest.moexservice.model;

public class Currency {
    String tradedate;
    String tradetime;
    String secid;
    String shortname;
    String price;
    String lasttoprevprice;
    String nominal;
    String decimals;

    public Currency() {
    }

    public String getTradedate() {
        return tradedate;
    }

    public void setTradedate(String tradedate) {
        this.tradedate = tradedate;
    }

    public String getTradetime() {
        return tradetime;
    }

    public void setTradetime(String tradetime) {
        this.tradetime = tradetime;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLasttoprevprice() {
        return lasttoprevprice;
    }

    public void setLasttoprevprice(String lasttoprevprice) {
        this.lasttoprevprice = lasttoprevprice;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getDecimals() {
        return decimals;
    }

    public void setDecimals(String decimals) {
        this.decimals = decimals;
    }
}
