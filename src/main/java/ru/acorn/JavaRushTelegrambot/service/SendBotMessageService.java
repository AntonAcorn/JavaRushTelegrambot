package ru.acorn.JavaRushTelegrambot.service;
//Send message via telegram bot.
public interface SendBotMessageService {
    void sendMessage(String chatId, String message);
}
