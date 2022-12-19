package ru.acorn.JavaRushTelegrambot.service;

import ru.acorn.JavaRushTelegrambot.javarushclient.dto.GroupDiscussionInfo;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;

public interface GroupSubService {
    GroupSub save (String chatId, GroupDiscussionInfo groupDiscussionInfo);
}
