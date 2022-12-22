package ru.acorn.JavaRushTelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String START_MESSAGE = "Привет. Я Javarush Telegram Bot. " +
            "Я помогу тебе быть в курсе последних статей тех авторов, котoрые тебе интересны.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {

        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(Long.valueOf(chatId)).ifPresentOrElse(telegramUser -> {
                    telegramUser.setActive(true);
                    telegramUserService.save(telegramUser);
                },
                () -> {
            TelegramUser telegramUser = new TelegramUser();
            telegramUser.setActive(true);
            telegramUser.setChatId(Long.valueOf(chatId));
            telegramUserService.save(telegramUser);
                });
        sendBotMessageService.sendMessage(Long.valueOf(chatId), START_MESSAGE);
    }
}
