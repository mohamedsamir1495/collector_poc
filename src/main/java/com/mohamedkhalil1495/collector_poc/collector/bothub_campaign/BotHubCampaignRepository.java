package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotHubCampaignRepository extends CrudRepository<BotHubCampaignEntity, Long> {
    List<BotHubCampaignEntity> findAllByStatusAndBttCampaign_Bot_IsActivatedAndBttCampaign_Bot_Error(boolean status,
                                                                                                     boolean isActivated,
                                                                                                     boolean error);
}