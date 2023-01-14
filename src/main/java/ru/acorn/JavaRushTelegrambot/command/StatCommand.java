package ru.acorn.JavaRushTelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.command.annotation.AdminCommand;
import ru.acorn.JavaRushTelegrambot.dto.StatisticDTO;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.StatisticsService;

import java.util.stream.Collectors;

import static ru.acorn.JavaRushTelegrambot.command.CommandUtils.getChatId;

@AdminCommand
public class StatCommand implements Command {

    private final StatisticsService statisticsService;
    private final SendBotMessageService sendBotMessageService;

    public final static String STAT_MESSAGE = "✨<b>Prepared statistics</b>✨\n" +
            "- Number of active users: %s\n" +
            "- Number of inactive users: %s\n" +
            "- Average number of groups per user: %s\n\n" +
            "<b>Information on active groups</b>:\n" +
            "%s";

    @Autowired
    public StatCommand(SendBotMessageService sendBotMessageService, StatisticsService statisticsService) {
        this.sendBotMessageService = sendBotMessageService;
        this.statisticsService = statisticsService;
    }

    @Override
    public void execute(Update update) {
        StatisticDTO statisticDTO = statisticsService.countBotStatistic();

        String collectedGroups = statisticDTO.getGroupStatDTOs().stream()
                .map(it -> String.format("%s (id = %s) - %s subscribers", it.getTitle(), it.getId(), it.getActiveUserCount()))
                .collect(Collectors.joining("\n"));

        sendBotMessageService.sendMessage(getChatId(update), String.format(STAT_MESSAGE,
                statisticDTO.getActiveUserCount(),
                statisticDTO.getInactiveUserCount(),
                statisticDTO.getAverageGroupCountByUser(),
                collectedGroups));
    }
}
