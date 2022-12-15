package ru.acorn.JavaRushTelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageServiceImpl;

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Привет. Я Javarush Telegram Bot. " +
            "Я помогу тебе быть в курсе последних статей тех авторов, котoрые тебе интересны.";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
