package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import com.mohamedkhalil1495.collector_poc.third_party.BotHubClient;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BotHubCamelRoutes extends RouteBuilder {

    @Autowired
    BotHubClient botHubClient;

    @Autowired
    BotHubCampaignService botHubCampaignService;

    @Override
    public void configure() throws Exception {
        from("direct:updatingBotHubCampaignInDatabase")
                .log("BTT Campaign to be updated: ${body}")
                .choice()
                .when(exchange -> botHubCampaignService.markBotHubCampaignWithStatus(
                        exchange.getIn().getBody(BotHubCampaignDTO.class),
                        BotHubCampaignStatus.FINISHED))
                .log("Bothub campaign marked with finished successfully")
                .otherwise()
                .log("Error updating BotHub campaign status with finished update was not successful");

        from("direct:callBotHub")
                .log("Calling BotHub with ${body}")
                .process(exchange -> {
                    BotHubCampaignDTO botHubCampaignDTO = exchange.getIn().getBody(BotHubCampaignDTO.class);
                    exchange
                            .getIn()
                            .setBody(botHubClient.getBotHubCampaignResults(botHubCampaignDTO));

                })
                .log("Bothub response ${body}")
                .to("direct:updateBotHubCampaignResultsInDatabase");


        from("direct:updateBotHubCampaignResultsInDatabase")
                .log("Calling BotHub with ${body}")
                .choice()
                .when(exchange -> botHubCampaignService.saveBotHubCampaignResults(exchange.getIn().getBody(BotHubCampaignDTO.class)))
                .log("Bothub campaign updated successfully")
                .otherwise()
                .log("Bothub campaign updated unsuccessfully");


    }
}
