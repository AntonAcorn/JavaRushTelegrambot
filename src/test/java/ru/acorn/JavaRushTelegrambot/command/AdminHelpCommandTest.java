package ru.acorn.JavaRushTelegrambot.command;

import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;

import static org.junit.jupiter.api.Assertions.*;
import static ru.acorn.JavaRushTelegrambot.command.AdminHelpCommand.ADMIN_HELP_MESSAGE;
import static ru.acorn.JavaRushTelegrambot.command.CommandName.ADMIN_HELP;

class AdminHelpCommandTest extends AbstractCommandTest{

    SendBotMessageService sendBotMessageService;
    @Override
    String getCommandName() {
        return ADMIN_HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return ADMIN_HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new AdminHelpCommand(sendBotMessageService);
    }
}