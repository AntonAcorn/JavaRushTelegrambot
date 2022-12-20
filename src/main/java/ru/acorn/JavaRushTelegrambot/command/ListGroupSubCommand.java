package ru.acorn.JavaRushTelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

import javax.ws.rs.NotFoundException;
import java.util.stream.Collectors;

public class ListGroupSubCommand implements Command{
    private final TelegramUserService telegramUserService;
    private final SendBotMessageService sendBotMessageService;

    public ListGroupSubCommand(TelegramUserService telegramUserService, SendBotMessageService sendBotMessageService) {
        this.telegramUserService = telegramUserService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        TelegramUser telegramUser = telegramUserService.findByChatId(update.getMessage().getChatId()
                .toString()).orElseThrow(NotFoundException::new);
        String message = "All subscriptions to the group: \n\n";
        String listGroup = telegramUser.getGroupSubs().stream().map(groupSub -> "Group: " + groupSub.getTitle() +
                " Group ID: " + groupSub.getId() + " \n").collect(Collectors.joining());

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),message + listGroup);
    }
}
