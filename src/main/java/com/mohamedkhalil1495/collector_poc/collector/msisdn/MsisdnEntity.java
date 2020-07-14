package com.mohamedkhalil1495.collector_poc.collector.msisdn;

import com.mohamedkhalil1495.collector_poc.base.BaseEntity;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignEntity;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "msisdn")
public class MsisdnEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "campaign_id")
    private BTTCampaignEntity bttCampaign;

    private String number;
}