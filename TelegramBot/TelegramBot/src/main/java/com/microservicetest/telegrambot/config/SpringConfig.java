package com.microservicetest.telegrambot.config;

import com.microservicetest.telegrambot.bot.WriteReadBot;
import com.microservicetest.telegrambot.bot.handlers.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class SpringConfig {
    private final TelegramConfig telegramConfig;

    @Autowired
    public SpringConfig(TelegramConfig telegramConfig) {
        this.telegramConfig = telegramConfig;
    }

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public WriteReadBot springWebhookBot(SetWebhook setWebhook,
                                         MessageHandler messageHandler/*,
                                         CallbackQueryHandler callbackQueryHandler*/) {
        WriteReadBot bot = new WriteReadBot(setWebhook, messageHandler/*, callbackQueryHandler*/);

        bot.setBotPath(telegramConfig.getWebhookPath());
        bot.setBotUsername(telegramConfig.getBotName());
        bot.setBotToken(telegramConfig.getBotToken());

        return bot;
    }
}
