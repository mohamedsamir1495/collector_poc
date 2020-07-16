package com.mohamedkhalil1495.collector_poc.core.btt_campaign.routes;

import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignDTO;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignService;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignStatus;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarkBTTCampaignWithStatusFinished extends RouteBuilder {

    @Autowired
    BTTCampaignService bttCampaignService;

    @Override
    public void configure() throws Exception {
        from("direct:markBTTCampaignWithStatusFinished")
                .process(exchange -> {
                    BTTCampaignDTO bttCampaignDTO = exchange.getIn().getBody(BTTCampaignDTO.class);
                    boolean result = bttCampaignService.markBTTCampaignWithStatus(bttCampaignDTO, BTTCampaignStatus.FINISHED);
                    if (result) {
                        exchange.getIn().setHeader("UPDATING_BTT_CAMPAIGN", Boolean.TRUE);
                        exchange.getIn().setBody(bttCampaignDTO.getBotHubCampaigns());
                    } else {
                        exchange.getIn().setHeader("UPDATING_BTT_CAMPAIGN", Boolean.FALSE);
                    }
                })
                .choice()
                .when(header("UPDATING_BTT_CAMPAIGN").isEqualTo(false))
                .log("Could not mark with status FINISHED BTT Campaign ${body}")
                .otherwise()
                .log("Bothub campaign updated successfully")
                .log("Will Start Marking BotHub Campaigns with Finished")
                .split(body())
                .to("direct:updatingBotHubCampaignInDatabase")
                .endChoice();
    }
}
