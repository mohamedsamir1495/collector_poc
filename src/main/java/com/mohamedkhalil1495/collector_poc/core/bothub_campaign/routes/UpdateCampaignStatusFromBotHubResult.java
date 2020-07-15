package com.mohamedkhalil1495.collector_poc.core.bothub_campaign.routes;

import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignDTO;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class UpdateCampaignStatusFromBotHubResult extends RouteBuilder {
    @Autowired
    BotHubCampaignService botHubCampaignService;
    @Override
    public void configure() throws Exception {
        from("direct:updateCampaignStatusFromBotHubResult").process(exchange ->
                exchange.getIn().setBody(botHubCampaignService
                        .saveBotHubCampaignResults(exchange.getIn().getBody(BotHubCampaignDTO.class))))
                .choice()
                .when(body().isEqualTo(true))
                .log("Bothub campaign updated successfully")
                .otherwise()
                .log("Bothub campaign updated unsuccessfully")
                .endChoice();

    }
}
