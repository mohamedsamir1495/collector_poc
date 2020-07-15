package com.mohamedkhalil1495.collector_poc.core.btt_campaign;

import com.mohamedkhalil1495.collector_poc.core.bot.BotMapper;
import com.mohamedkhalil1495.collector_poc.core.bot.BotRepository;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BTTCampaignService  {

    private final BTTCampaignRepository bttCampaignRepository;
    private final BotRepository botRepository;
    private final BTTCampaignMapper bttCampaignMapper;
    private final BotMapper botMapper;
    private final BotHubCampaignMapper botHubCampaignMapper;

    public BTTCampaignService(BTTCampaignRepository bttCampaignRepository,
                                      BotRepository botRepository,
                                      BTTCampaignMapper bttCampaignMapper,
                                      BotMapper botMapper,
                                      BotHubCampaignMapper botHubCampaignMapper) {
        this.bttCampaignRepository = bttCampaignRepository;
        this.botRepository = botRepository;
        this.bttCampaignMapper = bttCampaignMapper;
        this.botMapper = botMapper;
        this.botHubCampaignMapper = botHubCampaignMapper;
    }

    public List<BTTCampaignDTO> getAllCampaignsByStatus(BTTCampaignStatus status) {
        log.info(
                "{}- Finding {} RTTool campaigns...",
                Thread.currentThread().getName(),
                status
        );
        List<BTTCampaignDTO> activeCampaigns = bttCampaignRepository
                .findAllByStatus(status)
                .stream()
                .map(entity -> {
                    BTTCampaignDTO campaign = bttCampaignMapper.toDto(entity, false);
                    BTTCampaignStatus evaluatedStatus = BTTCampaignStatus.evaluateCampaignStatus(campaign);
                    campaign.setStatus(evaluatedStatus);
                    campaign.setBot(botMapper.toDto(entity.getBot()));
                    return campaign;
                })
                .collect(Collectors.toList());
        log.info(
                "{}- Found ({}) {} RTTool campaigns",
                Thread.currentThread().getName(),
                activeCampaigns.size(),
                status
        );
        return activeCampaigns;
    }



}