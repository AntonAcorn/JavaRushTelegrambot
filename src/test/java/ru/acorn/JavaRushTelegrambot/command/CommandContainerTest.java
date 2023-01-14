package ru.acorn.JavaRushTelegrambot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.acorn.JavaRushTelegrambot.javarushclient.JavaRushGroupClient;
import ru.acorn.JavaRushTelegrambot.service.*;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class CommandContainerTest {
    private CommandContainer commandContainer;

    @BeforeEach
    void setUp() {
        SendBotMessageServiceImpl service = Mockito.mock(SendBotMessageServiceImpl.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        JavaRushGroupClient javaRushGroupClient = Mockito.mock(JavaRushGroupClient.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        StatisticsService statisticsService = Mockito.mock(StatisticsService.class);
        commandContainer = new CommandContainer(service, telegramUserService, javaRushGroupClient,
                groupSubService,singletonList("username"),
                statisticsService
                );
    }

    @Test
    void findCommand() {
        Arrays.stream(CommandName.values()).forEach(commandName -> {
            Command command = commandContainer.findCommand(commandName.getCommandName(), "username");
            assertNotEquals(UnknownCommand.class, command.getClass());
        });
    }

    @Test
    void findUnknownCommand() {
        String unknownCommand = "/asdasd";
        Command command = commandContainer.findCommand(unknownCommand, "username");
        assertEquals(UnknownCommand.class, command.getClass());
    }
}