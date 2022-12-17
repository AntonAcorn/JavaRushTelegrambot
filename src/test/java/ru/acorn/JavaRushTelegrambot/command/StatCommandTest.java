package ru.acorn.JavaRushTelegrambot.command;

import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.STAT;
import static ru.acorn.JavaRushTelegrambot.command.StatCommand.STAT_MESSAGE;

public class StatCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(STAT_MESSAGE,0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(service, telegramUserService);
    }
}
