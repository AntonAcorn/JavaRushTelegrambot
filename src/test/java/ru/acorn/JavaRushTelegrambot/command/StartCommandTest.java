package ru.acorn.JavaRushTelegrambot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.acorn.JavaRushTelegrambot.command.CommandName.START;
import static ru.acorn.JavaRushTelegrambot.command.StartCommand.START_MESSAGE;


class StartCommandTest extends AbstractCommandTest {
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
            return new StartCommand(service);
    }
}