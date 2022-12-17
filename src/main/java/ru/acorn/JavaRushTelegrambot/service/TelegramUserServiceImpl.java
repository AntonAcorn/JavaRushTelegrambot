package ru.acorn.JavaRushTelegrambot.service;

import org.springframework.stereotype.Service;
import ru.acorn.JavaRushTelegrambot.repository.TelegramUserRepository;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;

    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository= telegramUserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return telegramUserRepository.findById(chatId);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUser() {
        return telegramUserRepository.findAllByActiveTrue();
    }
}
