package ru.acorn.JavaRushTelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.STAT;

public class AdminHelpCommand implements Command{
    public static final String ADMIN_HELP_MESSAGE = String.format("✨Available admin commands✨\n"
                    + "Get statistics\n"
                    + "%s - bot statistics\n",
            STAT.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public AdminHelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(Long.valueOf(update.getMessage().getChatId().toString()), ADMIN_HELP_MESSAGE);
    }
}
