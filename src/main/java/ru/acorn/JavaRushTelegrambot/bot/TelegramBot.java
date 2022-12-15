package ru.acorn.JavaRushTelegrambot.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.command.CommandContainer;
import ru.acorn.JavaRushTelegrambot.config.BotConfig;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageServiceImpl;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.NO;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final CommandContainer commandContainer;

    public static String COMMAND_PREFIX = "/";

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String message = update.getMessage().getText().trim();
            if(message.startsWith(COMMAND_PREFIX)){
                String commandIdentifier =  message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            }else{
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
}
