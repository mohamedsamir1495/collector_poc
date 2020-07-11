package com.mohamedkhalil1495.collector_poc.config;

import com.mohamedkhalil1495.collector_poc.collector.bot.BotDTO;
import com.mohamedkhalil1495.collector_poc.collector.bot.BotMapper;
import com.mohamedkhalil1495.collector_poc.collector.bot.BotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class SeedDatabase {

    @Bean
    CommandLineRunner initDatabase(BotRepository botRepository, BotMapper botMapper) {
        return args -> {
//            log.info("Preloading " + botRepository.save(botMapper.toEntity(
//                    new BotDTO(1, "Bot_Test", "BOTHUB_ID", true, false), false)));
//            log.info("Preloading " + botRepository.save(botMapper.toEntity(
//                    new BotDTO(1, "Bot_Test", "BOTHUB_ID", true, false), false)));
        };
    }
}
