package ru.acorn.JavaRushTelegrambot.command;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.START;
import static ru.acorn.JavaRushTelegrambot.command.StartCommand.START_MESSAGE;


public class StartCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
            return new StartCommand(service, telegramUserService);
    }
}