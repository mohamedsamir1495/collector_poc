package com.mohamedkhalil1495.collector_poc.core.btt_campaign;


import com.mohamedkhalil1495.collector_poc.core.bot.BotMapper;
import com.mohamedkhalil1495.collector_poc.core.bot.BotRepository;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignDTO;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignMapper;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BTTCampaignService {

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

    @Transactional
    public List<BTTCampaignDTO> fetchBTTCampaignsFromDataBaseByStatusToCheckTheirBotHubResults(BTTCampaignStatus status) {
        log.info("Fetched list with status"+status);
        List<BTTCampaignDTO> activeCampaigns = bttCampaignRepository
                .fetchBTTCampaignsFromDataBaseByStatusToCheckTheirBotHubResults(status)
                .stream()
                .map(entity -> bttCampaignMapper.toDto(entity, true))
                .collect(Collectors.toList());

        log.info("Fetched list",activeCampaigns);
        return activeCampaigns;
    }

    @Transactional
    public boolean markBTTCampaignWithStatus(BTTCampaignDTO bttCampaignDTO, BTTCampaignStatus status) {
        try {
            bttCampaignRepository.updateBTTCampaignWithStatus(bttCampaignDTO.getId(), status);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



}