package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.routes;

import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignDTO;
import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignService;
import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignStatus;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateBotHubStatus extends RouteBuilder {
    @Autowired
    BotHubCampaignService botHubCampaignService;
    @Override
    public void configure() throws Exception {
        from("direct:updatingBotHubCampaignInDatabase")
                .log("BTT Campaign to be updated: ${body}")
                .process( exchange ->    exchange.getIn().setBody(botHubCampaignService.markBotHubCampaignWithStatus(
                        exchange.getIn().getBody(BotHubCampaignDTO.class),
                        BotHubCampaignStatus.FINISHED)))
                .choice()
                .when(body().isEqualTo(true))
                .log("Bothub campaign marked with finished successfully")
                .otherwise()
                .log("Error updating BotHub campaign status with finished update was not successful")
                .end();
    }
}
