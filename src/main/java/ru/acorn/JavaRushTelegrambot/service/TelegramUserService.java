package ru.acorn.JavaRushTelegrambot.service;

import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {
    void save (TelegramUser telegramUser);
    Optional<TelegramUser> findByChatId(String chatId);
    List<TelegramUser> retrieveAllActiveUser();
}
