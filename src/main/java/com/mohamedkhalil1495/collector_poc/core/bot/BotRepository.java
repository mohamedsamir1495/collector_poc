package com.mohamedkhalil1495.collector_poc.core.bot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BotRepository extends CrudRepository<BotEntity, Long> {

}
