package com.mohamedkhalil1495.collector_poc.collector.bot;

import com.mohamedkhalil1495.collector_poc.annotation.Mapper;
import com.mohamedkhalil1495.collector_poc.base.ObjectMapper;


import java.util.Objects;

@Mapper
public class BotMapper extends ObjectMapper<BotDTO, BotEntity> {

    @Override
    public BotDTO toDto(BotEntity entity, boolean eager) {
        if (Objects.isNull(entity)) return null;
        return new BotDTO() {{
            setId(entity.getId());
            setName(entity.getName());
            setBotHubId(entity.getBotHubId());
            setStatus(entity.getStatus());
        }};
    }

    @Override
    public BotEntity toEntity(BotDTO dto, boolean eager) {
        if (Objects.isNull(dto)) return null;
        BotEntity entity = new BotEntity();
        entity.setBotHubId(dto.getBotHubId());
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}