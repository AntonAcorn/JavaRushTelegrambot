package ru.acorn.JavaRushTelegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.command.CommandContainer;
import ru.acorn.JavaRushTelegrambot.config.BotConfig;
import ru.acorn.JavaRushTelegrambot.javarushclient.JavaRushGroupClient;
import ru.acorn.JavaRushTelegrambot.service.GroupSubService;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageServiceImpl;
import ru.acorn.JavaRushTelegrambot.service.StatisticsService;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

import java.util.List;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.NO;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final CommandContainer commandContainer;
    //private final TelegramUserService telegramUserService;

    public static String COMMAND_PREFIX = "/";

    public TelegramBot(BotConfig botConfig,
                       TelegramUserService telegramUserService,
                       JavaRushGroupClient javaRushGroupClient,
                       GroupSubService groupSubService,
                       @Value("#{'${bot.admins}'.split(',')}") List<String> admins,
                       StatisticsService statisticsService) {
        this.botConfig = botConfig;
        //this.telegramUserService = telegramUserService;
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService,
                javaRushGroupClient, groupSubService, admins, statisticsService);
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String username = update.getMessage().getFrom().getUserName();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.findCommand(commandIdentifier, username).execute(update);
            } else {
                commandContainer.findCommand(NO.getCommandName(), username).execute(update);
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
}
