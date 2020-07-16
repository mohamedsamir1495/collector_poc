package com.mohamedkhalil1495.collector_poc.core.bothub_campaign.routes;

import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignDTO;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignService;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignStatus;
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
                .threads(5,10,"ZZZ")
                .log("BotHub Campaign to be updated: ${body}")
                .process( exchange ->    exchange.getIn().setBody(botHubCampaignService.markBotHubCampaignWithStatus(
                        exchange.getIn().getBody(BotHubCampaignDTO.class),
                        BotHubCampaignStatus.FINISHED)))
                .choice()
                .when(body().isEqualTo(true))
                .log("BotHub campaign marked with finished successfully")
                .otherwise()
                .log("Error updating BotHub campaign status with finished update was not successful")
                .end();
    }
}
