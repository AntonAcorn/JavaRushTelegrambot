package ru.acorn.JavaRushTelegrambot.service;

import org.springframework.stereotype.Service;
import ru.acorn.JavaRushTelegrambot.javarushclient.JavaRushPostClient;
import ru.acorn.JavaRushTelegrambot.javarushclient.dto.PostInfo;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindNewArticleServiceImpl implements FindNewArticleService {
    public static final String JAVARUSH_WEB_POST_FORMAT = "https://javarush.com/groups/posts/%s";

    private final GroupSubService groupSubService;
    private final JavaRushPostClient javaRushPostClient;
    private final SendBotMessageService sendMessageService;

    public FindNewArticleServiceImpl(GroupSubService groupSubService, JavaRushPostClient javaRushPostClient, SendBotMessageService sendMessageService) {
        this.groupSubService = groupSubService;
        this.javaRushPostClient = javaRushPostClient;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void findNewArticles() {
        groupSubService.findAll().forEach(groupSub -> {
            List<PostInfo> newPosts = javaRushPostClient.findNewPosts(groupSub.getId(),groupSub.getLastArticleId());

            setNewLastArticleId(groupSub, newPosts);

            notifySubscribersAboutNewArticle(groupSub, newPosts);
        });
    }

    private void notifySubscribersAboutNewArticle(GroupSub groupSub, List<PostInfo> newPosts) {
        Collections.reverse(newPosts);
        List<String> messageWithNewArticle = newPosts.stream()
                .map(postInfo -> String.format("✨Вышла новая статья %s в группе %s✨\n" +
                                "Описание:  %s\n" +
                                "Ссылка:  %s\n",
                        postInfo.getTitle(), groupSub.getTitle(), postInfo.getDescription(), getPostUrl(postInfo.getKey())))
               .collect(Collectors.toList());
    }

    private void setNewLastArticleId(GroupSub groupSub, List<PostInfo> newPosts) {
        newPosts.stream().mapToInt(PostInfo::getId).max().ifPresent(id -> {
            groupSub.setLastArticleId(id);
            groupSubService.save(groupSub);
        });
        
    }
    private String getPostUrl(String key) {
        return String.format(JAVARUSH_WEB_POST_FORMAT, key);
    }
}
