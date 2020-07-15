package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.routes;

import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignDTO;
import com.mohamedkhalil1495.collector_poc.third_party.BotHubClient;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class GetCampaignStatusFromBotHub  extends RouteBuilder {
    @Autowired
    BotHubClient botHubClient;
    @Override
    public void configure() throws Exception {
        from("direct:getCampaignStatusFromBotHub")
                .log("Calling BotHub with ${body}")
                .process(exchange -> {
                    BotHubCampaignDTO botHubCampaignDTO = exchange.getIn().getBody(BotHubCampaignDTO.class);
                    exchange
                            .getIn()
                            .setBody(botHubClient.getBotHubCampaignResults(botHubCampaignDTO));
                })
                .log("Bothub response ${body}")
                .to("direct:updatingBotHubCampaignInDatabase");

    }
}
