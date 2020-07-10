package com.mohamedkhalil1495.collector_poc.collector.btt_campaign;

import com.mohamedkhalil1495.collector_poc.base.BaseDto;


import com.mohamedkhalil1495.collector_poc.collector.bot.BotDTO;
import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignDTO;
import com.mohamedkhalil1495.collector_poc.collector.msisdn.MsisdnDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BTTCampaignDTO implements BaseDto {

    private long id;

    private BotDTO bot;

    private String name;

    private Status status;

    private List<BotHubCampaignDTO> botHubCampaigns;

    private List<MsisdnDTO> msisdns;

    public enum Status {NOT_STARTED, ACTIVE, STOPPED, BOT_ERROR, FINISHED}
}