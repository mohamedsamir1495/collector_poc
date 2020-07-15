package com.mohamedkhalil1495.collector_poc.core.msisdn;

import com.mohamedkhalil1495.collector_poc.base.BaseDto;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignDTO;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MsisdnDTO implements BaseDto {

    private long id;

    private BTTCampaignDTO bttCampaign;

    private String number;
}