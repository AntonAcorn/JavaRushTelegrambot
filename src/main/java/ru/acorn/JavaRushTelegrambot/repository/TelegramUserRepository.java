package ru.acorn.JavaRushTelegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;

import java.util.List;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long>{
    List<TelegramUser> findAllByActiveTrue();

    List<TelegramUser> findAllByActiveFalse();
}
