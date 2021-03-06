package com.mohamedkhalil1495.collector_poc.core.bothub_campaign;

import com.mohamedkhalil1495.collector_poc.annotation.Mapper;
import com.mohamedkhalil1495.collector_poc.base.ObjectMapper;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Mapper
public class BotHubCampaignMapper extends ObjectMapper<BotHubCampaignDTO, BotHubCampaignEntity> {

    private BTTCampaignMapper bttCampaignMapper;

    @Autowired
    public void setRtToolCampaignMapper(BTTCampaignMapper bttCampaignMapper) {
        this.bttCampaignMapper = bttCampaignMapper;
    }

    @Override
    public BotHubCampaignDTO toDto(BotHubCampaignEntity entity, boolean eager) {
        if (Objects.isNull(entity)) return null;

        return new BotHubCampaignDTO() {{
            setId(entity.getId());
            setBotHubId(entity.getBotHubId());
            setStatus(entity.getStatus());
            setSentCount(entity.getSentCount());
            setDeliveredCount(entity.getDeliveredCount());
            setReadCount(entity.getReadCount());
            setTotalCount(entity.getTotalCount());
            setErrorCount(entity.getErrorCount());
            if (eager) {
                setBttCampaign(bttCampaignMapper.toDto(entity.getBttCampaign(), false));
            }
        }};
    }

    public BotHubCampaignDTO toSpecialDto(BotHubCampaignEntity entity,
                                          boolean eagerLoadBot,
                                          boolean eagerLoadMsisdns,
                                          boolean eagerLoadBotHubCampaigns) {
        if (Objects.isNull(entity)) return null;

        return new BotHubCampaignDTO() {{
            setId(entity.getId());
            setBotHubId(entity.getBotHubId());
            setStatus(entity.getStatus());
            setSentCount(entity.getSentCount());
            setDeliveredCount(entity.getDeliveredCount());
            setReadCount(entity.getReadCount());
            setTotalCount(entity.getTotalCount());
            setErrorCount(entity.getErrorCount());
            if (eagerLoadBot || eagerLoadMsisdns || eagerLoadBotHubCampaigns) {
                setBttCampaign(bttCampaignMapper.toSpecialDto(entity.getBttCampaign(), eagerLoadBot, eagerLoadMsisdns, eagerLoadBotHubCampaigns));
            }
        }};
    }

    @Override
    public BotHubCampaignEntity toEntity(BotHubCampaignDTO dto, boolean eager) {
        if (Objects.isNull(dto)) return null;
        BotHubCampaignEntity entity = new BotHubCampaignEntity();
        entity.setId(dto.getId());
        entity.setBotHubId(dto.getBotHubId());
        entity.setStatus(dto.getStatus());
        entity.setSentCount(dto.getSentCount());
        entity.setDeliveredCount(dto.getDeliveredCount());
        entity.setReadCount(dto.getReadCount());
        entity.setTotalCount(dto.getTotalCount());
        entity.setErrorCount(dto.getErrorCount());
        if (eager) {
            entity.setBttCampaign(bttCampaignMapper.toEntity(dto.getBttCampaign(), false));
        }
        return entity;
    }
}