package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import com.mohamedkhalil1495.collector_poc.base.BaseEntity;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignEntity;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "bot_hub_campaign")
public class BotHubCampaignEntity implements BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "btt_campaign_id")
    private BTTCampaignEntity bttCampaign;

    @Column(name = "bothub_campaign_id")
    private String botHubId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BotHubCampaignStatus status;

    @Column(name = "sent_count")
    private int sentCount;

    @Column(name = "delivered_count")
    private int deliveredCount;

    @Column(name = "read_count")
    private int readCount;

    @Column(name = "error_count")
    private int errorCount;

    @Column(name = "total_count")
    private int totalCount;


}
