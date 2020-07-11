package com.mohamedkhalil1495.collector_poc.collector.btt_campaign;

import com.mohamedkhalil1495.collector_poc.base.BaseEntity;
import com.mohamedkhalil1495.collector_poc.collector.bot.BotEntity;
import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignEntity;
import com.mohamedkhalil1495.collector_poc.collector.msisdn.MsisdnEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "campaign")
@Entity
public class BTTCampaignEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "bot_id")
    private BotEntity bot;

    private String name;

    private int status;

    @OneToMany(
            mappedBy = "bttCampaign",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private Set<BotHubCampaignEntity> botHubCampaigns;

    @OneToMany(
            mappedBy = "bttCampaign",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private Set<MsisdnEntity> msisdns;
}
