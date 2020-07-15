package com.mohamedkhalil1495.collector_poc.core.bothub_campaign.routes;

import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignDTO;
import com.mohamedkhalil1495.collector_poc.third_party.BotHubClient;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GetCampaignStatusFromBotHub  extends RouteBuilder {
    @Value("${BOTHUB.COLLECT_CAMPAIGN_RESULTS.CONCURRENT_REQUEST_LIMIT}")
    private Integer bothubConcurrentRequestLimit;

    @Autowired
    BotHubClient botHubClient;
    @Override
    public void configure() throws Exception {
        from("direct:getCampaignStatusFromBotHub")
                .threads(bothubConcurrentRequestLimit,bothubConcurrentRequestLimit)
                .process(exchange -> {
                    log.info("Calling BotHub to fetch BotHub Campaign Results ..."+Thread.currentThread().getName());

                    BotHubCampaignDTO botHubCampaignDTO = exchange.getIn().getBody(BotHubCampaignDTO.class);
                    exchange
                            .getIn()
                            .setBody(botHubClient.getBotHubCampaignResults(botHubCampaignDTO));
                })
                .log("Bothub response ${body}")
                .to("direct:updateCampaignStatusFromBotHubResult");

    }
}
