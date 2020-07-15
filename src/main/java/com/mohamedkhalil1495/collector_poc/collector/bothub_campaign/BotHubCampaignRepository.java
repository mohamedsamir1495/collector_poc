package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotHubCampaignRepository extends CrudRepository<BotHubCampaignEntity, Long> {

    @Query(value = "select \n" +
            "bot_hub_campaign.*\n" +
            "from bot_hub_campaign \n" +
            "inner join btt_campaign on btt_campaign.id = bot_hub_campaign.btt_campaign_id\n" +
            "inner join bot on  bot.id = btt_campaign.bot_id\n" +
            "where bot_hub_campaign.status=\"RUNNING\" and bot.status =\"ACTIVATED\" and btt_campaign.status = \"TRIGGERING\"",
            nativeQuery = true)
    List<BotHubCampaignEntity> getAllRunningBotHubCampaignsToCollectTheirBothubResults();


    @Query(value = "select \n" +
            "bot_hub_campaign.*\n" +
            "from bot_hub_campaign \n" +
            "inner join btt_campaign on btt_campaign.id = bot_hub_campaign.btt_campaign_id\n" +
            "inner join bot on  bot.id = btt_campaign.bot_id\n" +
            "where bot.status =\"ACTIVATED\" and btt_campaign.status = \"FINISHED\"",
            nativeQuery = true)
    List<BotHubCampaignEntity> getAllRunningBotHubCampaignsWithFinishedBTTCampaigns();

    @Modifying
    @Query("update BotHubCampaignEntity record" +
            " set record.status = ?2," +
            "record.sentCount = ?3, " +
            "record.deliveredCount = ?4, " +
            "record.readCount = ?5, " +
            "record.errorCount = ?6 " +
            "where record.id = ?1")
    void updateBotHubCampaignWithBotHubResults(long recordId,
                                               BotHubCampaignStatus status,
                                               int sentCount, int deliveredCount, int readCount, int errorCount);

    @Modifying
    @Query("update BotHubCampaignEntity record set record.status = ?2 where record.id = ?1")
    void updateBotHubCampaignWithStatus(long recordId, BotHubCampaignStatus status);
}
