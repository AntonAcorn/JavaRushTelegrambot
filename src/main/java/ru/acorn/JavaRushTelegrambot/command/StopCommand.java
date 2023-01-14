package ru.acorn.JavaRushTelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

public class StopCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private  final TelegramUserService telegramUserService;

    public final static String STOP_MESSAGE = "Deactivated all your subscriptions";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }


    @Override
    public void execute(Update update) {
    sendBotMessageService.sendMessage(update.getMessage().getChatId(), STOP_MESSAGE);
    telegramUserService.findByChatId(Long.valueOf(update.getMessage().getChatId().toString()))
            .ifPresent(telegramUser -> {
        telegramUser.setActive(false);
        telegramUserService.save(telegramUser);
    });
    }
}
