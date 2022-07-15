package com.microservicetest.telegrambot.bot;

import com.microservicetest.telegrambot.bot.model.Rate;
import com.microservicetest.telegrambot.bot.service.HistoricalServiceClient;
import com.microservicetest.telegrambot.config.TelegramConfig;
import com.microservicetest.telegrambot.view.ButtonNameEnum;
import com.microservicetest.telegrambot.view.ReplyKeyboardMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class TelegramBot extends SpringWebhookBot {
    private final HistoricalServiceClient historicalServiceClient;
    private final ReplyKeyboardMaker replyKeyboardMaker;
    private final TelegramConfig telegramConfig;

    @Autowired
    public TelegramBot(/*SetWebhook setWebhook,*/ TelegramConfig telegramConfig,
                       HistoricalServiceClient historicalServiceClient, ReplyKeyboardMaker replyKeyboardMaker) {
        super(SetWebhook.builder().url(telegramConfig.getWebhookPath()).build());
        this.historicalServiceClient = historicalServiceClient;
        this.replyKeyboardMaker = replyKeyboardMaker;
        this.telegramConfig = telegramConfig;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (IllegalArgumentException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    "Illegal arg");
        } catch (Exception e) {
            e.printStackTrace();
            return new SendMessage(update.getMessage().getChatId().toString(),
                    "Welcome to the unknown error");
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) throws IOException {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return null;
        } else {
            Message message = update.getMessage();
            SendMessage sendMessage = (SendMessage) answerMessage(message);
            return sendMessage;
        }
    }

    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();

        String inputText = message.getText();
        System.out.println(inputText);

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            return getStartMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.GET_USD.getButtonName()) ||
                   inputText.equals(ButtonNameEnum.GET_EUR.getButtonName()) ||
                   inputText.equals(ButtonNameEnum.GET_CNY.getButtonName())) {
            return new SendMessage(chatId,
                prepareMessage(historicalServiceClient.getLastRateValueFromHistoricalService(inputText).block()));
        }
        else {
            return new SendMessage(chatId, "Unknown command");
        }
    }

    public String prepareMessage(Rate rate) {
        return rate.getName() + " rate:" + rate.getPrice() + " traded at " + rate.getTradeTime();
    }

    @PostConstruct
    public void initWebHook () {
        String url = telegramConfig.getApiURL() + "bot" + telegramConfig.getBotToken()
                        + "/setWebhook?url=" + telegramConfig.getWebhookPath();
        System.out.println("Initiating Web Hook: " + url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());
    }

    private SendMessage getStartMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Help message");
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    @Override
    public String getBotUsername() {
        return telegramConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return telegramConfig.getBotToken();
    }

    @Override
    public String getBotPath() {
        return telegramConfig.getWebhookPath();
    }
}
