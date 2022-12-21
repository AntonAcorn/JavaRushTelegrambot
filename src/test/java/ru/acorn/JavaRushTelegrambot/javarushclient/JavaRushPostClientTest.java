package ru.acorn.JavaRushTelegrambot.javarushclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.acorn.JavaRushTelegrambot.javarushclient.dto.PostInfo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.acorn.JavaRushTelegrambot.javarushclient.JavaRushGroupClientTest.JAVARUSH_API_PATH;

class JavaRushPostClientTest {

    JavaRushPostClient javaRushPostClient = new JavaRushPostClientImpl(JAVARUSH_API_PATH);

    @Test
    void findNewPosts() {
        List<PostInfo> postInfoList = javaRushPostClient.findNewPosts(30, 2935);

        Assertions.assertEquals(15, postInfoList.size());
    }
}