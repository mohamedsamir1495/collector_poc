//package com.mohamedkhalil1495.collector_poc.collector.routes;
//
//import com.mohamedkhalil1495.collector_poc.collector.bothub_campaign.BotHubCampaignEntity;
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CollectorObservableRoute extends RouteBuilder {
//    public static final String ROUTE_ID = CollectorObservableRoute.class.getSimpleName();
//
//    @Override
//    public void configure() throws Exception {
//        from("jpa://"+ BotHubCampaignEntity.class.getName()+"?consumeDelete=false&nativeQuery=select * from bot_hub_campaign").log("{Body}").
//        to("log:foo");
//
//    }
//}
