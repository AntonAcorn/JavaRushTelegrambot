package ru.acorn.JavaRushTelegrambot.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TelegramUserRepositoryIT {
   private final TelegramUserRepository telegramUserRepository;

   @Autowired
    public TelegramUserRepositoryIT(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    @Test
    public void shouldProperlyFindAllActive(){
    //when
        List<TelegramUser> users = telegramUserRepository.findAllByActiveTrue();
    //then
        Assertions.assertEquals(5, users.size());
    }

    @Sql(scripts = {"/sql/clearDbs.sql"})
    @Test
    public void shouldProperlySaveUser(){
        //given
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId("123344");
        telegramUser.setActive(false);
        telegramUserRepository.save(telegramUser);

        //when
        Optional<TelegramUser> saved = telegramUserRepository.findById(telegramUser.getChatId());

        //then
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(telegramUser, saved.get());
    }
    @Test
    @Sql(scripts = {"/sql/fiveGroupSubsForUser.sql"})
    public void shouldProperlyGetAllGroupSubsForUser(){
        //when
        Optional <TelegramUser> userFromBd = telegramUserRepository.findById("1");

        Assertions.assertTrue(userFromBd.isPresent());

        List<GroupSub> groups = userFromBd.get().getGroupSubs();
        for (int i = 0; i < groups.size(); i++) {
            Assertions.assertEquals(String.format("g%s",(i+1)), groups.get(i).getTitle());
            Assertions.assertEquals(i+1, groups.get(i).getId());
            Assertions.assertEquals(i+1, groups.get(i).getLastArticleId());
        }

    }
}
