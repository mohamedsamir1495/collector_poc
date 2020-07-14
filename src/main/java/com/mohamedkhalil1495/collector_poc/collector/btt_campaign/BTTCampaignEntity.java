package com.mohamedkhalil1495.collector_poc.collector.btt_campaign;

import com.mohamedkhalil1495.collector_poc.base.BaseEntity;
import com.mohamedkhalil1495.collector_poc.collector.bot.BotEntity;
import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignEntity;
import com.mohamedkhalil1495.collector_poc.collector.msisdn.MsisdnEntity;
import lombok.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "btt_campaign")
public class BTTCampaignEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "bot_id")
    private BotEntity bot;

    private String name;

    @Enumerated(EnumType.STRING)
    private BTTCampaignStatus status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name="trigger_start_time")
    private LocalTime triggerStartTime;

    @Column(name="trigger_end_time")
    private LocalTime triggerEndTime;

    @Column(name = "total_msisdns")
    private int totalMsisdns;

    @Column(name = "total_sent")
    private int totalSent;

    @Column(name = "total_delivered")
    private int totalDelivered;

    @Column(name = "total_read")
    private int totalRead;

    @OneToMany(
            mappedBy = "bttCampaign",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private Set<BotHubCampaignEntity> botHubCampaigns;

    @OneToMany(
            mappedBy = "bttCampaign",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private Set<MsisdnEntity> msisdns;
}
