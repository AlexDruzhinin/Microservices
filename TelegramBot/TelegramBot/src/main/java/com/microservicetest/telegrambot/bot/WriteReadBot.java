package com.microservicetest.telegrambot.bot;

import com.microservicetest.telegrambot.bot.handlers.MessageHandler;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.io.IOException;

public class WriteReadBot extends SpringWebhookBot {
    private String botPath;
    private String botUsername;
    private String botToken;

    MessageHandler messageHandler;
    //CallbackQueryHandler callbackQueryHandler;

    public WriteReadBot(SetWebhook setWebhook, MessageHandler messageHandler/*, CallbackQueryHandler callbackQueryHandler*/) {
        super(setWebhook);
        this.messageHandler = messageHandler;
       // this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (IllegalArgumentException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    "Illegal arg");
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    "Welcome to the unknown error");
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) throws IOException {
        if (update.hasCallbackQuery()) {
            /*CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.processCallbackQuery(callbackQuery);*/
        } else {
            Message message = update.getMessage();
            if (message != null) {
                return messageHandler.answerMessage(update.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    public void setBotPath(String botPath) {
        this.botPath = botPath;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    /*public CallbackQueryHandler getCallbackQueryHandler() {
        return callbackQueryHandler;
    }*/

    /*public void setCallbackQueryHandler(CallbackQueryHandler callbackQueryHandler) {
        this.callbackQueryHandler = callbackQueryHandler;
    }*/
}
