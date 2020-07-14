package com.mohamedkhalil1495.collector_poc.collector.bot;

import com.mohamedkhalil1495.collector_poc.base.BaseDto;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BotDTO implements BaseDto {

    private long id;

    private String name;

    private String botHubId;

    private BotStatus status;
}