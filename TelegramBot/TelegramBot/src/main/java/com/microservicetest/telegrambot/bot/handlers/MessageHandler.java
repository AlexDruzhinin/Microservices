package com.microservicetest.telegrambot.bot.handlers;

import com.microservicetest.telegrambot.bot.TelegramApiClient;
import com.microservicetest.telegrambot.view.ButtonNameEnum;
import com.microservicetest.telegrambot.view.ReplyKeyboardMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageHandler {

    private final TelegramApiClient telegramApiClient;
    private final ReplyKeyboardMaker replyKeyboardMaker;

    @Autowired
    public MessageHandler(TelegramApiClient telegramApiClient, ReplyKeyboardMaker replyKeyboardMaker) {
        this.telegramApiClient = telegramApiClient;
        this.replyKeyboardMaker = replyKeyboardMaker;
    }

    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();

        String inputText = message.getText();

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            return getStartMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.GET_USD.getButtonName())) {
            return getUsdMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.GET_EUR.getButtonName())) {
            return getEurMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.GET_CNY.getButtonName())) {
            return getCnyMessage(chatId);
        }  else {
            return new SendMessage(chatId, "Unknown command");
        }
    }

    private SendMessage getStartMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Help message");
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

    private SendMessage getUsdMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "USD is too cheap");
        return sendMessage;
    }

    private SendMessage getEurMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "EUR is too cheap");
        return sendMessage;
    }

    private SendMessage getCnyMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "CHINA IS STRONG");
        return sendMessage;
    }
}
