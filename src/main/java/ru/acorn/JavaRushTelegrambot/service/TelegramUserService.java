package ru.acorn.JavaRushTelegrambot.service;

import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {

    void save(TelegramUser telegramUser);

    List<TelegramUser> findAllActiveUsers();

    List<TelegramUser> findAllInActiveUsers();

    Optional<TelegramUser> findByChatId(Long chatId);
}
