package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import com.mohamedkhalil1495.collector_poc.collector.bot.BotMapper;
import com.mohamedkhalil1495.collector_poc.collector.bot.BotRepository;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignDTO;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignMapper;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BotHubCampaignService {

    private final BotHubCampaignRepository botHubCampaignRepository;
    private final BotRepository botRepository;

    private final BTTCampaignMapper bttCampaignMapper;
    private final BotMapper botMapper;
    private final BotHubCampaignMapper botHubCampaignMapper;

    public BotHubCampaignService(BotHubCampaignRepository botHubCampaignRepository,
                                 BotRepository botRepository,
                                 BotHubCampaignMapper botHubCampaignMapper,
                                 BTTCampaignMapper bttCampaignMapper,
                                 BotMapper botMapper)
    {
        this.botHubCampaignRepository = botHubCampaignRepository;
        this.botHubCampaignMapper = botHubCampaignMapper;
        this.botRepository = botRepository;
        this.botMapper = botMapper;
        this.bttCampaignMapper = bttCampaignMapper;
    }

    public List<BotHubCampaignDTO> getAllBotHubCampaignsByStatus(BotHubCampaignStatus status) {
        log.info( "{}- Finding {} BotHub Campaign ...", Thread.currentThread().getName(), status);
        List<BotHubCampaignDTO> activeCampaigns = botHubCampaignRepository
                .getAllRunningBotHubCampaignsToCollectTheirBothubResults()
                .stream()
                .map(entity -> {
                    BotHubCampaignDTO campaign = botHubCampaignMapper.toDto(entity, false);
//                    BTTCampaignStatus evaluatedStatus = BTTCampaignStatus.evaluateCampaignStatus(campaign.getBttCampaign());
//                    campaign.setStatus(evaluatedStatus);
//                    campaign.setBot(botMapper.toDto(entity.getBot()));
                    return campaign;
                })
                .collect(Collectors.toList());
        log.info("{}- Found {} BotHub Campaign ...", Thread.currentThread().getName(), activeCampaigns.size(), status);

        return activeCampaigns;
    }
}
