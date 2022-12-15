package ru.acorn.JavaRushTelegrambot.command;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.NO;
import static ru.acorn.JavaRushTelegrambot.command.NoCommand.NO_MESSAGE;

public class NoCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(service);
    }
}
