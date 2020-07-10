package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import com.mohamedkhalil1495.collector_poc.base.BaseDto;

import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BotHubCampaignDTO implements BaseDto {

    private long id;

    private BTTCampaignDTO bttCampaign;

    private String botHubId;

    private int sentCount;

    private int deliveredCount;

    private int readCount;

    private int errorCount;

    private int totalCount;
}