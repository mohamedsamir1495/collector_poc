package com.mohamedkhalil1495.collector_poc.collector.routes;

import org.apache.camel.builder.RouteBuilder;

public class FetchBotHubCampaignsFromDB extends RouteBuilder {

    public static final String ROUTE_ID = FetchBotHubCampaignsFromDB.class.getSimpleName();

    @Override
    public void configure() {
        from("direct:fetchBotHubCampaignsFromDB")
                .routeId(ROUTE_ID)
                .log("fetching campaigns from Db ya walaaa !!!");

    }
}
