package com.mohamedkhalil1495.collector_poc.collector.btt_campaign;

import com.mohamedkhalil1495.collector_poc.annotation.Mapper;
import com.mohamedkhalil1495.collector_poc.base.ObjectMapper;
import com.mohamedkhalil1495.collector_poc.collector.bot.BotMapper;

import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignMapper;
import com.mohamedkhalil1495.collector_poc.collector.msisdn.MsisdnMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public class BTTCampaignMapper extends ObjectMapper<BTTCampaignDTO, BTTCampaignEntity> {

    private BotMapper botMapper;
    private BotHubCampaignMapper botHubCampaignMapper;
    private MsisdnMapper msisdnMapper;

    @Autowired
    public void setBotMapper(BotMapper botMapper) {
        this.botMapper = botMapper;
    }

    @Autowired
    public void setRtToolCampaignMapper(BotHubCampaignMapper botHubCampaignMapper) {
        this.botHubCampaignMapper = botHubCampaignMapper;
    }

    @Autowired
    public void setMsisdnMapper(MsisdnMapper msisdnMapper) {
        this.msisdnMapper = msisdnMapper;
    }

    @Override
    public BTTCampaignDTO toDto(BTTCampaignEntity entity, boolean eager) {
        if (Objects.isNull(entity)) return null;
        BTTCampaignDTO dto = new BTTCampaignDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(BTTCampaignDTO.Status.values()[entity.getStatus()]);
        if (eager) {
            dto.setBot(botMapper.toDto(entity.getBot(), false));
            if (entity.getBotHubCampaigns() == null) {
                entity.setBotHubCampaigns(new HashSet<>());
            }
            dto.setBotHubCampaigns(entity.getBotHubCampaigns()
                    .stream()
                    .map(botHubCampaign -> botHubCampaignMapper.toDto(botHubCampaign, false))
                    .collect(Collectors.toList())
            );
            if (entity.getMsisdns() == null) {
                entity.setMsisdns(new HashSet<>());
            }
            dto.setMsisdns(entity.getMsisdns()
                    .stream()
                    .map(msisdn -> msisdnMapper.toDto(msisdn, false))
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }

    @Override
    public BTTCampaignEntity toEntity(BTTCampaignDTO dto, boolean eager) {
        BTTCampaignEntity entity = new BTTCampaignEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus().ordinal());

        if (eager) {
            entity.setBot(botMapper.toEntity(dto.getBot(), false));
            if (dto.getBotHubCampaigns() == null) {
                dto.setBotHubCampaigns(new ArrayList<>());
            }
            entity.setBotHubCampaigns(dto.getBotHubCampaigns()
                    .stream()
                    .map(botHubCampaign -> botHubCampaignMapper.toEntity(botHubCampaign, false))
                    .collect(Collectors.toSet())
            );
            if (dto.getMsisdns() == null) {
                dto.setMsisdns(new ArrayList<>());
            }
            entity.setMsisdns(dto.getMsisdns()
                    .stream()
                    .map(msisdn -> msisdnMapper.toEntity(msisdn, false))
                    .collect(Collectors.toSet())
            );
        }
        return entity;
    }
}