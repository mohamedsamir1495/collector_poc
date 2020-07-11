package com.mohamedkhalil1495.collector_poc.collector.bot;

import com.mohamedkhalil1495.collector_poc.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bot")
public class BotEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "bot_id")
    private String botHubId;

    @Column(name = "is_activated")
    private boolean isActivated;

    private boolean error;

}