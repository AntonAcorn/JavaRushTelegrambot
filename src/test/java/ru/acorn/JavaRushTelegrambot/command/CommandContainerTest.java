package ru.acorn.JavaRushTelegrambot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageServiceImpl;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommandContainerTest {
    private CommandContainer commandContainer;

    @BeforeEach
    void setUp() {
        SendBotMessageServiceImpl service = Mockito.mock(SendBotMessageServiceImpl.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        commandContainer = new CommandContainer(service, telegramUserService);
    }

    @Test
    void retrieveCommand() {//check all commands//. Все команды, которые поддерживает бот, находятся в списке CommandName и
        // должны быть в контейнере. Поэтому я взял все переменные CommandName, перешел в Stream API и для каждого
        // выполнил поиск команды из контейнера. Если бы такой команды не было,
        // была бы возвращена команда UnknownCommand.
        Arrays.stream(CommandName.values()).forEach(commandName -> {
           Command command = commandContainer.retrieveCommand(commandName.getCommandName());
            assertNotEquals(UnknownCommand.class, command.getClass());
        });
    }

    @Test
    void retrieveUnknownCommand(){
        String unknownCommand = "/asdasd";
        Command command = commandContainer.retrieveCommand(unknownCommand);
        assertEquals(UnknownCommand.class, command.getClass());
    }
}