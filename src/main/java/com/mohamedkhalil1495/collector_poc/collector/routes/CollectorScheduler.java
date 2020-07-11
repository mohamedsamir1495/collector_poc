package com.mohamedkhalil1495.collector_poc.collector.routes;

import org.apache.camel.builder.RouteBuilder;


public class CollectorScheduler extends RouteBuilder {

    public static final String ROUTE_ID = CollectorScheduler.class.getSimpleName();

    public static final String SCHEDULER_TYPE = "COLLECTOR_SCHEDULER";

    public static final String SCHEDULER_ACTION = "START_COLLECTOR_JOB";


    @Override
    public void configure() {
        from("quartz://collectorScheduler?cron={{scheduler-routes.collector.cron-expression}}")
                .routeId(ROUTE_ID)
                .log("Collector scheduler starting a new cycle")
                .setHeader(SCHEDULER_TYPE, constant(SCHEDULER_ACTION))
                .to("direct:fetchBotHubCampaignsFromDB");
    }
}