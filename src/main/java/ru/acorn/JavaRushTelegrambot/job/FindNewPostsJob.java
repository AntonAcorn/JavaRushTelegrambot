package ru.acorn.JavaRushTelegrambot.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Slf4j
public class FindNewPostsJob {
    private final FindNewPostsJob findNewPostsJob;

    public FindNewPostsJob(FindNewPostsJob findNewPostsJob) {
        this.findNewPostsJob = findNewPostsJob;
    }
    @Scheduled(fixedRateString = "${bot.recountNewPostFixedRate}")
    public void findNewPosts() {
        LocalDateTime start = LocalDateTime.now();

        log.info("Find new article job started.");

        findNewPostsJob.findNewPosts();

        LocalDateTime end = LocalDateTime.now();

        log.info("Find new articles job finished. Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}

