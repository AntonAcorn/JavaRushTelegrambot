package ru.acorn.JavaRushTelegrambot.command;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.HELP;
import static ru.acorn.JavaRushTelegrambot.command.HelpCommand.HELP_MESSAGE;

public class HelpCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(service);
    }
}
