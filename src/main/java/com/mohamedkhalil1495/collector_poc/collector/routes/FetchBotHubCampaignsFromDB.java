package com.mohamedkhalil1495.collector_poc.collector.routes;

import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignRepository;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignService;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignDTO;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignService;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.BTTCampaignStatus;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.pojo.BTTCampaignsGroup;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
                .process(exchange ->
                {
                    List<BTTCampaignDTO> fetchedCampaignsFromDB = exchange.getIn().getBody(List.class);
                    exchange.getIn()
                            .setBody(bttCampaignService.separateTriggeredAndFinishedCampaignsIntoTwoGroupsAndDiscardOtherCampaigns(fetchedCampaignsFromDB));
                })
                .log("BTT Campaigns separated to 2 groups !!!")
                .split(body())
                .process(exchange -> exchange.getIn().setHeader("BTT_CAMPAIGNS_GROUP_TYPE", exchange.getIn().getBody(BTTCampaignsGroup.class).getStatus()))
                .log("Group ${body}")
                .choice()
                .when(header("BTT_CAMPAIGNS_GROUP_TYPE").isEqualTo(BTTCampaignStatus.FINISHED))
                .process(exchange -> exchange.getIn().setBody(exchange.getIn().getBody(BTTCampaignsGroup.class).getGroup()))
                .to("direct:splitBTTCampaignsToSendItIndividuallyToMarkItInDatabaseAsFinished")

                .when(header("BTT_CAMPAIGNS_GROUP_TYPE").isEqualTo(BTTCampaignStatus.TRIGGERING))
                .process(exchange -> exchange.getIn().setBody(exchange.getIn().getBody(BTTCampaignsGroup.class).getGroup()))
                .to("direct:shuffleBotHubCampaignIdsBeforeCheckingTheirBotHubResults")
                .endChoice();


        from("direct:splitBTTCampaignsToSendItIndividuallyToMarkItInDatabaseAsFinished")
                .split(body())
                .log("Updating database ${body}")
                .to("direct:markBTTCampaignWithStatusFinished");

        from("direct:shuffleBotHubCampaignIdsBeforeCheckingTheirBotHubResults")
                .process(exchange ->
                {
                    List<BTTCampaignDTO> fetchedTriggeringCampaignsFromDB = exchange.getIn().getBody(List.class);
                    exchange.getIn()
                            .setBody(bttCampaignService.shuffleBotHubCampaignsBeforeGettingResultsFromBotHub(fetchedTriggeringCampaignsFromDB));
                })
                .split(body())
                .log("Calling BotHub ${body}")
                .to("direct:getCampaignStatusFromBotHub");
    }
}
