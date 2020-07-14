package com.mohamedkhalil1495.collector_poc.collector.bot;

import com.mohamedkhalil1495.collector_poc.base.BaseEntity;

import lombok.*;


import javax.persistence.*;
import java.time.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "bot")
public class BotEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "bot_id")
    private String botHubId;

    @Enumerated(EnumType.STRING)
    private BotStatus status;

}