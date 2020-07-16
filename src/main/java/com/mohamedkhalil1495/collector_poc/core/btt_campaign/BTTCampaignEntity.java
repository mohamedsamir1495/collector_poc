package com.mohamedkhalil1495.collector_poc.core.btt_campaign;

import com.mohamedkhalil1495.collector_poc.base.BaseEntity;
import com.mohamedkhalil1495.collector_poc.core.bot.BotEntity;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignEntity;
import com.mohamedkhalil1495.collector_poc.core.msisdn.MsisdnEntity;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
    private Integer totalMsisdns;

    @Column(name = "total_sent")
    private Integer totalSent;

    @Column(name = "total_delivered")
    private Integer totalDelivered;

    @Column(name = "total_read")
    private Integer totalRead;

    @Column(name = "total_error")
    private Integer totalError;

    @OneToMany(
            mappedBy = "bttCampaign",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @NotFound(action= NotFoundAction.IGNORE)
    private Set<BotHubCampaignEntity> botHubCampaigns;

    @OneToMany(
            mappedBy = "bttCampaign",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @NotFound(action= NotFoundAction.IGNORE)
    private Set<MsisdnEntity> msisdns;

    @Override
    public String toString() {
        return "BTTCampaignEntity{" +
                "id=" + id +
                ", bot=" + bot +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", triggerStartTime=" + triggerStartTime +
                ", triggerEndTime=" + triggerEndTime +
                ", totalMsisdns=" + totalMsisdns +
                ", totalSent=" + totalSent +
                ", totalDelivered=" + totalDelivered +
                ", totalRead=" + totalRead +
                ", botHubCampaigns=" + botHubCampaigns +
                ", msisdns=" + msisdns +
                '}';
    }
}
