package com.mohamedkhalil1495.collector_poc.core.btt_campaign;

import com.mohamedkhalil1495.collector_poc.annotation.Mapper;
import com.mohamedkhalil1495.collector_poc.base.ObjectMapper;
import com.mohamedkhalil1495.collector_poc.core.bot.BotMapper;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignMapper;
import com.mohamedkhalil1495.collector_poc.core.msisdn.MsisdnMapper;
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
        dto.setStatus(entity.getStatus());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setTriggerStartTime(entity.getTriggerStartTime());
        dto.setTriggerEndTime(entity.getTriggerEndTime());
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
        dto.setTotalMsisdns(entity.getTotalMsisdns());
        dto.setTotalSent(entity.getTotalSent());
        dto.setTotalDelivered(entity.getTotalDelivered());
        dto.setTotalRead(entity.getTotalRead());

        return dto;
    }

    @Override
    public BTTCampaignEntity toEntity(BTTCampaignDTO dto, boolean eager) {
        BTTCampaignEntity entity = new BTTCampaignEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setTriggerStartTime(dto.getTriggerStartTime());
        entity.setTriggerEndTime(dto.getTriggerEndTime());
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

        entity.setTotalMsisdns(dto.getTotalMsisdns());
        entity.setTotalSent(dto.getTotalSent());
        entity.setTotalDelivered(dto.getTotalDelivered());
        entity.setTotalRead(dto.getTotalRead());
        return entity;
    }

    public BTTCampaignDTO toSpecialDto(BTTCampaignEntity entity,
                                       boolean eagerLoadBot,
                                       boolean eagerLoadMsisdns,
                                       boolean eagerLoadBotHubCampaigns) {
        if (Objects.isNull(entity)) return null;
        BTTCampaignDTO dto = new BTTCampaignDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setTriggerStartTime(entity.getTriggerStartTime());
        dto.setTriggerEndTime(entity.getTriggerEndTime());
        if (eagerLoadBot || eagerLoadMsisdns || eagerLoadBotHubCampaigns) {
            if (eagerLoadBot)
                dto.setBot(botMapper.toDto(entity.getBot(), eagerLoadBot));

            if (eagerLoadBotHubCampaigns) {
                if (entity.getBotHubCampaigns() == null) {
                    entity.setBotHubCampaigns(new HashSet<>());
                }
                dto.setBotHubCampaigns(entity.getBotHubCampaigns()
                        .stream()
                        .map(botHubCampaign -> botHubCampaignMapper.toDto(botHubCampaign, eagerLoadBotHubCampaigns))
                        .collect(Collectors.toList())
                );
            }

            if (eagerLoadMsisdns) {
                if (entity.getMsisdns() == null) {
                    entity.setMsisdns(new HashSet<>());
                }
                dto.setMsisdns(entity.getMsisdns()
                        .stream()
                        .map(msisdn -> msisdnMapper.toDto(msisdn, eagerLoadMsisdns))
                        .collect(Collectors.toList())
                );
            }
        }
        dto.setTotalMsisdns(entity.getTotalMsisdns());
        dto.setTotalSent(entity.getTotalSent());
        dto.setTotalDelivered(entity.getTotalDelivered());
        dto.setTotalRead(entity.getTotalRead());

        return dto;
    }

    public BTTCampaignEntity toSpecialEntity(BTTCampaignDTO dto,
                                             boolean eagerLoadBot,
                                             boolean eagerLoadMsisdns,
                                             boolean eagerLoadBotHubCampaigns) {
        BTTCampaignEntity entity = new BTTCampaignEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setTriggerStartTime(dto.getTriggerStartTime());
        entity.setTriggerEndTime(dto.getTriggerEndTime());

        if (eagerLoadBot || eagerLoadMsisdns || eagerLoadBotHubCampaigns) {
            entity.setBot(botMapper.toEntity(dto.getBot(), eagerLoadBot));
            if (dto.getBotHubCampaigns() == null) {
                dto.setBotHubCampaigns(new ArrayList<>());
            }
            entity.setBotHubCampaigns(dto.getBotHubCampaigns()
                    .stream()
                    .map(botHubCampaign -> botHubCampaignMapper.toEntity(botHubCampaign, eagerLoadBotHubCampaigns))
                    .collect(Collectors.toSet())
            );
            if (dto.getMsisdns() == null) {
                dto.setMsisdns(new ArrayList<>());
            }
            entity.setMsisdns(dto.getMsisdns()
                    .stream()
                    .map(msisdn -> msisdnMapper.toEntity(msisdn, eagerLoadMsisdns))
                    .collect(Collectors.toSet())
            );
        }

        entity.setTotalMsisdns(dto.getTotalMsisdns());
        entity.setTotalSent(dto.getTotalSent());
        entity.setTotalDelivered(dto.getTotalDelivered());
        entity.setTotalRead(dto.getTotalRead());
        return entity;
    }
}