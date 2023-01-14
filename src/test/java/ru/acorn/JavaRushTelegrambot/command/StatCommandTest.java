package ru.acorn.JavaRushTelegrambot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.acorn.JavaRushTelegrambot.dto.GroupStatDTO;
import ru.acorn.JavaRushTelegrambot.dto.StatisticDTO;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.StatisticsService;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

import java.util.Collections;

import static java.lang.String.format;
import static ru.acorn.JavaRushTelegrambot.command.AbstractCommandTest.prepareUpdate;
import static ru.acorn.JavaRushTelegrambot.command.CommandName.STAT;
import static ru.acorn.JavaRushTelegrambot.command.StatCommand.STAT_MESSAGE;

public class StatCommandTest {

    private SendBotMessageService sendBotMessageService;
    private StatisticsService statisticsService;
    private Command statCommand;

    @BeforeEach
    public void init() {
        sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        statisticsService = Mockito.mock(StatisticsService.class);
        statCommand = new StatCommand(sendBotMessageService, statisticsService);
    }

    @Test
    public void shouldProperlySendMessage() {
        //given
        Long chatId = 1234567L;
        GroupStatDTO groupDto = new GroupStatDTO(1, "group", 1);
        StatisticDTO statisticDTO = new StatisticDTO(1, 1, Collections.singletonList(groupDto), 2.5);
        Mockito.when(statisticsService.countBotStatistic())
                .thenReturn(statisticDTO);

        //when
        statCommand.execute(prepareUpdate(chatId, CommandName.STAT.getCommandName()));

        //then
        Mockito.verify(sendBotMessageService).sendMessage(chatId, format(STAT_MESSAGE,
                statisticDTO.getActiveUserCount(),
                statisticDTO.getInactiveUserCount(),
                statisticDTO.getAverageGroupCountByUser(),
                format("%s (id = %s) - %s subscribers", groupDto.getTitle(), groupDto.getId(), groupDto.getActiveUserCount())));
    }

}
