package ru.acorn.JavaRushTelegrambot.javarushclient.dto;

import lombok.Data;

@Data
public class MeGroupInfo    {
    private MeGroupInfoStatus status;
    private Integer userGroupId;
}
