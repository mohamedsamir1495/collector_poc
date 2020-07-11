package com.mohamedkhalil1495.collector_poc.collector.routes;

import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchBotHubCampaignsFromDB extends RouteBuilder {

    public static final String ROUTE_ID = FetchBotHubCampaignsFromDB.class.getSimpleName();

    @Autowired
    BotHubCampaignRepository botHubCampaignRepository;

    @Override
    public void configure() {
        from("direct:fetchBotHubCampaignsFromDB")
                .routeId(ROUTE_ID)
                .log("fetching campaigns from Db ya walaaa !!!")
//                .bean(botHubCampaignRepository.findAllByStatusAndBttCampaign_Bot_IsActivatedAndBttCampaign_Bot_Error
//                        (false, true, false))
                .bean(botHubCampaignRepository.findAll())
                .log("Campaign fetched !!!");

    }
}
