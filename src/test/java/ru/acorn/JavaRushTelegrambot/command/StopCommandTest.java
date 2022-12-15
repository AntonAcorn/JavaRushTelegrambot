package ru.acorn.JavaRushTelegrambot.command;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.STOP;
import static ru.acorn.JavaRushTelegrambot.command.StopCommand.STOP_MESSAGE;

public class StopCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(service);
    }
}
