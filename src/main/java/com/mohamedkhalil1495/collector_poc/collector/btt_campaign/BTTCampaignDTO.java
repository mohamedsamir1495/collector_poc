package com.mohamedkhalil1495.collector_poc.collector.btt_campaign;

import com.mohamedkhalil1495.collector_poc.base.BaseDto;


import com.mohamedkhalil1495.collector_poc.collector.bot.BotDTO;
import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignDTO;
import com.mohamedkhalil1495.collector_poc.collector.msisdn.MsisdnDTO;

import lombok.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.scheduling.annotation.Scheduled;


import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BTTCampaignDTO implements BaseDto {

    private long id;

    private BotDTO bot;

    private String name;

    private BTTCampaignStatus status;

    private List<BotHubCampaignDTO> botHubCampaigns;

    private List<MsisdnDTO> msisdns;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime triggerStartTime;

    private LocalTime triggerEndTime;

    private int totalMsisdns;

    private int totalSent;

    private int totalDelivered;

    private int totalRead;

    @Override
    public String toString() {
        return "BTTCampaignDTO{" +
                "id=" + id +
                ", bot=" + bot +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", botHubCampaigns=" + botHubCampaigns +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", triggerStartTime=" + triggerStartTime +
                ", triggerEndTime=" + triggerEndTime +
                ", totalMsisdns=" + totalMsisdns +
                ", totalSent=" + totalSent +
                ", totalDelivered=" + totalDelivered +
                ", totalRead=" + totalRead +
                '}';
    }
}