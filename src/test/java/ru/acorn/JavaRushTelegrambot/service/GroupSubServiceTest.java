package ru.acorn.JavaRushTelegrambot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.acorn.JavaRushTelegrambot.javarushclient.dto.GroupDiscussionInfo;
import ru.acorn.JavaRushTelegrambot.repository.GroupSubRepository;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;

import java.util.Optional;

public class GroupSubServiceTest {
    private GroupSubService groupSubService;
    private GroupSubRepository groupSubRepository;
    private TelegramUser newUser;

    private final static String CHAT_ID = "1";

    @BeforeEach
    public void init() {
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        groupSubRepository = Mockito.mock(GroupSubRepository.class);
        groupSubService = new GroupSubServiceImpl(groupSubRepository, telegramUserService);

        newUser = new TelegramUser();
        newUser.setActive(true);
        newUser.setChatId(Long.valueOf(CHAT_ID));

        Mockito.when(telegramUserService.findByChatId(Long.valueOf(CHAT_ID))).thenReturn(Optional.of(newUser));
    }

    @Test
    public void shouldProperlySaveGroup(){
        //given
        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(newUser);

        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        //проверяет, что в объекте groupSubRepository был вызван метод save с аргументом expectedGroupSub.
        Mockito.verify(groupSubRepository).save(expectedGroupSub);

    }
    @Test
    public void shouldProperlyAddUserToExistingGroup() {
        //given
        TelegramUser oldTelegramUser = new TelegramUser();
        oldTelegramUser.setChatId(2L);
        oldTelegramUser.setActive(true);

        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");

        GroupSub groupFromDB = new GroupSub();
        groupFromDB.setId(groupDiscussionInfo.getId());
        groupFromDB.setTitle(groupDiscussionInfo.getTitle());
        groupFromDB.addUser(oldTelegramUser);

        Mockito.when(groupSubRepository.findById(groupDiscussionInfo.getId())).thenReturn(Optional.of(groupFromDB));

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(oldTelegramUser);
        expectedGroupSub.addUser(newUser);

        //when
        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        //then
        Mockito.verify(groupSubRepository).findById(groupDiscussionInfo.getId());
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }

}
