package com.mohamedkhalil1495.collector_poc.core.btt_campaign.pojo;

import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignDTO;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BTTCampaignsGroup {
    BTTCampaignStatus status;
    List<BTTCampaignDTO> group;
}
