package ru.acorn.JavaRushTelegrambot.service;

import ru.acorn.JavaRushTelegrambot.javarushclient.dto.GroupDiscussionInfo;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;

import java.util.Optional;

public interface GroupSubService {
    GroupSub save (String chatId, GroupDiscussionInfo groupDiscussionInfo);
    Optional<GroupSub> findById(Integer id);
    GroupSub save(GroupSub groupSub);
}
