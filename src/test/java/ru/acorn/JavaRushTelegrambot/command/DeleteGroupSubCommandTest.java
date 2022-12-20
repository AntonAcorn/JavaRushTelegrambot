package ru.acorn.JavaRushTelegrambot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;
import ru.acorn.JavaRushTelegrambot.service.GroupSubService;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static ru.acorn.JavaRushTelegrambot.command.AbstractCommandTest.prepareUpdate;
import static ru.acorn.JavaRushTelegrambot.command.CommandName.DELETE_GROUP_SUB;


class DeleteGroupSubCommandTest {

    private Command command;
    private SendBotMessageService sendBotMessageService;
    GroupSubService groupSubService;
    TelegramUserService telegramUserService;


}
