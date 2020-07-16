package com.mohamedkhalil1495.collector_poc.core.bot;

import com.mohamedkhalil1495.collector_poc.base.BaseEntity;

import lombok.*;


import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "bot")
public class BotEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "btt_bot_id")
    private Long id;

    private String name;

    @Column(name = "bothub_bot_id")
    private String botHubId;

    @Enumerated(EnumType.STRING)
    private BotStatus status;

}