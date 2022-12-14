package ru.acorn.JavaRushTelegrambot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.acorn.JavaRushTelegrambot.config.BotConfig;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start": startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                break;
                default: sendMessage(chatId, "Sorry");
            }
        }

    }

    private void startCommandReceived(long chatId, String name){
        String answer = "Hi, " + name;
        sendMessage(chatId,answer);
    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage sm = new SendMessage();
        sm.setChatId(String.valueOf(chatId));
        sm.setText(textToSend);
        try{
            execute(sm);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
}
