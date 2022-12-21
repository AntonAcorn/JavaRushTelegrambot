package ru.acorn.JavaRushTelegrambot.javarushclient;

import ru.acorn.JavaRushTelegrambot.javarushclient.dto.PostInfo;

import java.util.List;

public interface JavaRushPostClient {
    List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);
}
