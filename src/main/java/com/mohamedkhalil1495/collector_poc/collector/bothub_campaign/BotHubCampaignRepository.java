package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotHubCampaignRepository extends CrudRepository<BotHubCampaignEntity, Long> {

}