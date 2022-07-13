package com.microservicetest.telegrambot.view;

public enum ButtonNameEnum {
    GET_USD("USD"),
    GET_EUR("EUR"),
    GET_CNY("CNY");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
