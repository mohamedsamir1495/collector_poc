package com.mohamedkhalil1495.collector_poc.core.btt_campaign;

import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BTTCampaignRepository extends CrudRepository<BTTCampaignEntity, Long> {


    List<BTTCampaignEntity> findAllByStatus(BTTCampaignStatus status);

    @Query(value = "select \n" +
            "btt_campaign.*,\n" +
            "bot.*,\n" +
            "bot_hub_campaign.*\n" +
            "from btt_campaign \n" +
            "inner join bot_hub_campaign on btt_campaign.id = bot_hub_campaign.btt_campaign_id \n" +
            "inner join bot on btt_campaign.bot_id = bot.btt_bot_id \n" +
            "where bot_hub_campaign.status=\"RUNNING\" and bot.status =\"ACTIVATED\" and btt_campaign.status = \"TRIGGERING\"",
            nativeQuery = true
    )
    List<BTTCampaignEntity> fetchBTTCampaignsFromDataBaseByStatusToCheckTheirBotHubResults(BTTCampaignStatus status);


    @Modifying
    @Query("update BTTCampaignEntity record set record.status = ?2 where record.id = ?1")
    void updateBTTCampaignWithStatus(long recordId, BTTCampaignStatus status);
}
