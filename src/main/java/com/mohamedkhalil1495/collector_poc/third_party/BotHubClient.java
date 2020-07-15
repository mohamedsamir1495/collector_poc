package com.mohamedkhalil1495.collector_poc.third_party;

import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignDTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class BotHubClient {

    public final ArrayList<BotHubCampaignDTO> triggeredCampaigns = new ArrayList<>();


    public BotHubCampaignDTO getBotHubCampaignResults(BotHubCampaignDTO botHubCampaignDTO) {

        Random random = new Random();
        if (isActive(botHubCampaignDTO)) {
            botHubCampaignDTO.setSentCount(
                    Math.max(botHubCampaignDTO.getSentCount(), random.nextInt(botHubCampaignDTO.getTotalCount() + 1))
            );
        }
        if (isActive(botHubCampaignDTO)) {
            botHubCampaignDTO.setDeliveredCount(
                    Math.max(botHubCampaignDTO.getDeliveredCount(), random.nextInt(botHubCampaignDTO.getTotalCount() + 1))
            );
        }
        if (isActive(botHubCampaignDTO)) {
            botHubCampaignDTO.setReadCount(
                    Math.max(botHubCampaignDTO.getReadCount(), random.nextInt(botHubCampaignDTO.getTotalCount() + 1))
            );
        }
        if (isActive(botHubCampaignDTO)) {
            botHubCampaignDTO.setErrorCount(
                    Math.max(botHubCampaignDTO.getErrorCount(), random.nextInt(botHubCampaignDTO.getTotalCount() + 1))
            );
        }
        return botHubCampaignDTO;
    }

    private boolean isActive(BotHubCampaignDTO botHubCampaign) {
        return botHubCampaign.getTotalCount() != botHubCampaign.getReadCount() + botHubCampaign.getErrorCount();
    }
}