package ru.acorn.JavaRushTelegrambot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.acorn.JavaRushTelegrambot.bot.TelegramBot;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {
    private final TelegramBot telegramBot;

    public SendBotMessageServiceImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try{
            telegramBot.execute(sendMessage);
        }catch (TelegramApiException e){
            //TODO
        }
    }


}
