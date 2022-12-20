package ru.acorn.JavaRushTelegrambot.command;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageServiceImpl;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.acorn.JavaRushTelegrambot.command.CommandName.LIST_GROUP_SUB;

class GroupSubListCommandTest {

    @Test
    void shouldProperlyShowGroupList() {
        //given
        TelegramUser user = new TelegramUser();
        user.setActive(true);
        user.setChatId("1");

        List<GroupSub> groupSubList = new ArrayList<>();
        groupSubList.add(populateGroupSub(1, "gs1"));
        groupSubList.add(populateGroupSub(2, "gs2"));
        groupSubList.add(populateGroupSub(3, "gs3"));
        groupSubList.add(populateGroupSub(4, "gs4"));

        user.setGroupSubs(groupSubList);

        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageServiceImpl.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);

        Mockito.when(telegramUserService.findByChatId(user.getChatId())).thenReturn(Optional.of(user));

        ListGroupSubCommand command = new ListGroupSubCommand(sendBotMessageService,telegramUserService);

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(Long.valueOf(user.getChatId()));
        Mockito.when(message.getText()).thenReturn(LIST_GROUP_SUB.getCommandName());
        update.setMessage(message);

        String collectedGroups = "All subscriptions to the group: \n\n" +
                user.getGroupSubs().stream()
                        .map(it -> "Group: " + it.getTitle() + " Group ID: " + it.getId() + " \n")
                        .collect(Collectors.joining());

        //when
        command.execute(update);

        //then
        Mockito.verify(sendBotMessageService).sendMessage(user.getChatId(), collectedGroups);
    }

    private GroupSub populateGroupSub(int id, String title) {
        GroupSub group = new GroupSub();
        group.setId(id);
        group.setTitle(title);
        return group;
    }


}