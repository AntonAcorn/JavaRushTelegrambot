package ru.acorn.JavaRushTelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageServiceImpl;

public class StopCommand implements Command{
    SendBotMessageService sendBotMessageService;

    public StopCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public final static String STOP_MESSAGE = "Деактивировал все ваши подписки";

    @Override
    public void execute(Update update) {
    sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
            STOP_MESSAGE);
    }
}
