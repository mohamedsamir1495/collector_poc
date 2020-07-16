package com.mohamedkhalil1495.collector_poc.collector.routes;

import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignRepository;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignService;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignDTO;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignService;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignStatus;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchBotHubCampaignsFromDB extends RouteBuilder {

    public static final String ROUTE_ID = FetchBotHubCampaignsFromDB.class.getSimpleName();

    @Autowired
    BotHubCampaignRepository botHubCampaignRepository;

    @Autowired
    BotHubCampaignService botHubCampaignService;

    @Autowired
    BTTCampaignService bttCampaignService;

    @Override
    public void configure() {

        from("direct:fetchBotHubCampaignsFromDB")
                .routeId(ROUTE_ID)
                .log("fetching campaigns from Db ya walaaa !!!")
                .process(exchange -> exchange
                        .getIn()
                        .setBody(bttCampaignService.fetchBTTCampaignsFromDataBaseByStatusToCheckTheirBotHubResults(BTTCampaignStatus.TRIGGERING)))
                .log("Fetch Btt Campaigns ${body}")
                .split(body())
                .log("Campaign fetched !!!")
                .process(exchange -> {
                    BTTCampaignDTO dto = exchange.getIn().getBody(BTTCampaignDTO.class);
                    log.info("dto original btt campaign status" + dto.getStatus());
                    BTTCampaignStatus evaluatedStatus = BTTCampaignStatus.evaluateCampaignStatus(dto);
                    log.info("dto evaluated btt campaign status" + evaluatedStatus);
                    exchange.getIn().setHeader("EVALUATED_BTT_CAMPAIGN", evaluatedStatus);
                })
                .choice()
                .when(header("EVALUATED_BTT_CAMPAIGN").isEqualTo(BTTCampaignStatus.FINISHED))
                .log("Updating database ${body}")
                .to("direct:markBTTCampaignWithStatusFinished")
                .when(header("EVALUATED_BTT_CAMPAIGN").isEqualTo(BTTCampaignStatus.TRIGGERING))
                .process(exchange -> exchange.getIn().setBody(exchange.getIn().getBody(BTTCampaignDTO.class).getBotHubCampaigns()))
                .split(body())
                .log("Calling BotHub ${body}")
                .to("direct:getCampaignStatusFromBotHub")
                .endChoice();

    }
}
