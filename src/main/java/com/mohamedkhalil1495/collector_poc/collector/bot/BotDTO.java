package com.mohamedkhalil1495.collector_poc.collector.bot;

import com.mohamedkhalil1495.collector_poc.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BotDTO implements BaseDto {

    private long id;

    private String name;

    private String botHubId;

    private boolean isActivated;

    private boolean error;
}