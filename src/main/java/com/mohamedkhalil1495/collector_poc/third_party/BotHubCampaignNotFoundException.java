package com.mohamedkhalil1495.collector_poc.third_party;

public class BotHubCampaignNotFoundException extends RuntimeException  {

    public BotHubCampaignNotFoundException(long bothubCampaignId) {
        super(String.format("Bot hub campaign %d not found", bothubCampaignId));
    }

    public BotHubCampaignNotFoundException(String bothubCampaignId) {
        super(String.format("Bot hub campaign %s not found", bothubCampaignId));
    }
}