package ru.acorn.JavaRushTelegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.acorn.JavaRushTelegrambot.bot.TelegramBot;

import static org.junit.jupiter.api.Assertions.*;

class SendBotMessageServiceTest {

   private SendBotMessageServiceImpl sendBotMessageService;
   private TelegramBot telegramBot;

    @BeforeEach
    void setUp() {
        telegramBot = Mockito.mock(TelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(telegramBot);
    }

    @Test
    void sendMessage() throws TelegramApiException {
        //given
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);

        //when
        sendBotMessageService.sendMessage(chatId, message);

        //then
        Mockito.verify(telegramBot).execute(sendMessage);
    }
}