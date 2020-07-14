package com.mohamedkhalil1495.collector_poc.collector.routes;

import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignRepository;
import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignService;
import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignStatus;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignService;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignStatus;
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

    @Override
    public void configure() {

        from("direct:fetchBotHubCampaignsFromDB")
                .routeId(ROUTE_ID)
                .log("fetching campaigns from Db ya walaaa !!!")
                .process(exchange ->
                    exchange.getIn().setBody(botHubCampaignService.getAllBotHubCampaignsByStatus(BotHubCampaignStatus.RUNNING))
                )
                .log("${body}")

                .log("Campaign fetched !!!");

    }
}
