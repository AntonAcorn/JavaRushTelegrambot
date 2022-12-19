package ru.acorn.JavaRushTelegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;

public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
}
