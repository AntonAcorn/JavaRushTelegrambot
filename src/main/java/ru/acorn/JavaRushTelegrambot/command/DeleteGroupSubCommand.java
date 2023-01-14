package ru.acorn.JavaRushTelegrambot.command;

import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.acorn.JavaRushTelegrambot.repository.entity.GroupSub;
import ru.acorn.JavaRushTelegrambot.repository.entity.TelegramUser;
import ru.acorn.JavaRushTelegrambot.service.GroupSubService;
import ru.acorn.JavaRushTelegrambot.service.SendBotMessageService;
import ru.acorn.JavaRushTelegrambot.service.TelegramUserService;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static ru.acorn.JavaRushTelegrambot.command.CommandName.DELETE_GROUP_SUB;
import static ru.acorn.JavaRushTelegrambot.command.CommandUtils.getChatId;
import static ru.acorn.JavaRushTelegrambot.command.CommandUtils.getMessage;

public class DeleteGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    private final GroupSubService groupSubService;

    public DeleteGroupSubCommand(SendBotMessageService sendBotMessageService, GroupSubService groupSubService,
                                 TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.groupSubService = groupSubService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(DELETE_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update).toString());
            return;
        }
        String groupId = getMessage(update).split(SPACE)[1];
        String chatId = getChatId(update).toString();
        if (isNumeric(groupId)) {
            Optional<GroupSub> optionalGroupSub = groupSubService.findById(Integer.valueOf(groupId));
            if (optionalGroupSub.isPresent()) {
                GroupSub groupSub = optionalGroupSub.get();
                TelegramUser telegramUser = telegramUserService.findByChatId(Long.valueOf(chatId)).orElseThrow(NotFoundException::new);
                groupSub.getUsers().remove(telegramUser);
                groupSubService.save(groupSub);
                sendBotMessageService.sendMessage(Long.valueOf(chatId), format("Unsubscribed from a group: %s", groupSub.getTitle()));
            } else {
                sendBotMessageService.sendMessage(Long.valueOf(chatId), "No such group found =/");
            }
        } else {
            sendBotMessageService.sendMessage(Long.valueOf(chatId), "Invalid group ID format.\n " +
                    "ID must be a positive integer");
        }
    }

    private void sendGroupIdList(String chatId) {
        String message;
        List<GroupSub> groupSubs = telegramUserService.findByChatId(Long.valueOf(chatId))
                .orElseThrow(NotFoundException::new)
                .getGroupSubs();
        if (CollectionUtils.isEmpty(groupSubs)) {
            message = "There are no group subscriptions yet. To add a subscription write /addGroupSub";
        } else {
            message = "To unsubscribe from a group - pass the command along with the group ID. \n" +
                    "For example: /deleteGroupSub 16 \n" +
                    "I prepared a list of all the groups you are subscribed to \n" +
                    "group name - group ID \n" +
                    "%s";

        }
        String userGroupSubData = groupSubs.stream()
                .map(group -> format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        sendBotMessageService.sendMessage(Long.valueOf(chatId), format(message, userGroupSubData));
    }
}