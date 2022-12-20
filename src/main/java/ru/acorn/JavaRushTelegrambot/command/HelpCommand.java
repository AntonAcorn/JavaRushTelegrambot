package ru.acorn.JavaRushTelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.*;

public class HelpCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public static final String HELP_MESSAGE = String.format("✨Available commands✨\n\n"

                    + "Start\\stop working with bot:\n"
                    + "%s - start working with me\n"
                    + "%s - stop working with me\n\n"

                    + "Subscriptions:\n"
                    + "%s - subscribe\n"
                    + "%s - get list groups you are subscribed\n\n"

                    + "%s - help\n"
                    + "%s - statistics\n"
                    + "%s - unsubscribe\n",
            START.getCommandName(), STOP.getCommandName(), ADD_GROUP_SUB.getCommandName(),
            LIST_GROUP_SUB.getCommandName(), HELP.getCommandName(), STAT.getCommandName(), DELETE_GROUP_SUB.getCommandName());

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
