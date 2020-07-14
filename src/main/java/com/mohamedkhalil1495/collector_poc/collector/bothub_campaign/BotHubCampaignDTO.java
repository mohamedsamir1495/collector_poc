package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import com.mohamedkhalil1495.collector_poc.base.BaseDto;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignDTO;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BotHubCampaignDTO implements BaseDto {

    private long id;

    private BTTCampaignDTO bttCampaign;

    private String botHubId;

    private BotHubCampaignStatus status;


    private int sentCount;

    private int deliveredCount;

    private int readCount;

    private int errorCount;

    private int totalCount;

    @Override
    public String toString() {
        return "BotHubCampaignDTO{" +
                "id=" + id +
                ", botHubId='" + botHubId + '\'' +
                ", status=" + status +
                ", sentCount=" + sentCount +
                ", deliveredCount=" + deliveredCount +
                ", readCount=" + readCount +
                ", errorCount=" + errorCount +
                ", totalCount=" + totalCount +
                '}';
    }
}