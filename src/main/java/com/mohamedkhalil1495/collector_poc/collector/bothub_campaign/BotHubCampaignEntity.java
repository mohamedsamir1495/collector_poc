package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import com.mohamedkhalil1495.collector_poc.base.BaseEntity;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "bot_hub_campaign")
public class BotHubCampaignEntity implements BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "campaign_id")
    private BTTCampaignEntity bttCampaign;

    @Column(name = "bot_hub_id")
    private String botHubId;

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
