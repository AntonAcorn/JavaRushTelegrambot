package ru.acorn.JavaRushTelegrambot.javarushclient;


import ru.acorn.JavaRushTelegrambot.javarushclient.dto.GroupDiscussionInfo;
import ru.acorn.JavaRushTelegrambot.javarushclient.dto.GroupInfo;
import ru.acorn.JavaRushTelegrambot.javarushclient.dto.GroupRequestArgs;
import ru.acorn.JavaRushTelegrambot.javarushclient.dto.GroupsCountRequestArgs;

import java.util.List;

/**
 * Client for Javarush Open API corresponds to Groups.
 */
public interface JavaRushGroupClient {

    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);

    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);

    Integer getGroupCount(GroupsCountRequestArgs countRequestArgs);

    GroupDiscussionInfo getGroupById(Integer id);
}
