package ru.acorn.JavaRushTelegrambot.service;

import org.checkerframework.checker.nullness.Opt;
import org.springframework.stereotype.Service;
import ru.acorn.JavaRushTelegrambot.javarushclient.dto.GroupDiscussionInfo;
import ru.acorn.JavaRushTelegrambot.repository.GroupSubRepository;
import ru.acorn.JavaRushTelegrambot.repository.TelegramUserRepository;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GroupSubServiceImpl implements GroupSubService {
    private final GroupSubRepository groupSubRepository;
    private final TelegramUserService telegramUserService;

    public GroupSubServiceImpl(GroupSubRepository groupSubRepository, TelegramUserService telegramUserService) {
        this.groupSubRepository = groupSubRepository;
        this.telegramUserService = telegramUserService;

    }

    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {
        TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
        GroupSub groupSub;

        Optional <GroupSub> groupSubFromBd = groupSubRepository.findById(groupDiscussionInfo.getId());
        if(groupSubFromBd.isPresent()){
            groupSub = groupSubFromBd.get();
            Optional<TelegramUser> first = groupSub.getUsers().stream()
                    .filter(user ->
                        user.getChatId().equalsIgnoreCase(chatId)
                    )
                    .findFirst();
            if(first.isEmpty()){
                groupSub.addUser(telegramUser);
            }
        }else {
            groupSub = new GroupSub();
            groupSub.addUser(telegramUser);
            groupSub.setId(groupDiscussionInfo.getId());
            groupSub.setTitle(groupDiscussionInfo.getTitle());

        }
        return groupSubRepository.save(groupSub);
    }

    public Optional<GroupSub> findById (Integer id){
        return groupSubRepository.findById(id);
    }

    @Override
    public GroupSub save(GroupSub groupSub) {
        return groupSubRepository.save(groupSub);
    }

    @Override
    public List<GroupSub> findAll() {
        return groupSubRepository.findAll();
    }
}
