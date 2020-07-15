package com.mohamedkhalil1495.collector_poc.core.msisdn;

import com.mohamedkhalil1495.collector_poc.annotation.Mapper;
import com.mohamedkhalil1495.collector_poc.base.ObjectMapper;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class MsisdnMapper extends ObjectMapper<MsisdnDTO, MsisdnEntity> {

    private BTTCampaignMapper bttCampaignMapper;

    @Autowired
    public void setBTTCampaignMapper(BTTCampaignMapper bttCampaignMapper) {
        this.bttCampaignMapper = bttCampaignMapper;
    }

    @Override
    public MsisdnDTO toDto(MsisdnEntity entity, boolean eager) {
        return new MsisdnDTO() {{
            setId(entity.getId());
            setNumber(entity.getNumber());
            if (eager) {
                setBttCampaign(bttCampaignMapper.toDto(entity.getBttCampaign(), false));
            }
        }};
    }

    @Override
    public MsisdnEntity toEntity(MsisdnDTO dto, boolean eager) {
        MsisdnEntity entity = new MsisdnEntity();
        entity.setId(dto.getId());
        entity.setNumber(dto.getNumber());
        if (eager) {
            entity.setBttCampaign(bttCampaignMapper.toEntity(dto.getBttCampaign(), false));
        }
        return entity;
    }
}
