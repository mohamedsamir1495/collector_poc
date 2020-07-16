package com.mohamedkhalil1495.collector_poc.core.btt_campaign;


import com.mohamedkhalil1495.collector_poc.core.bot.BotMapper;
import com.mohamedkhalil1495.collector_poc.core.bot.BotRepository;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignDTO;
import com.mohamedkhalil1495.collector_poc.core.bothub_campaign.BotHubCampaignMapper;
import com.mohamedkhalil1495.collector_poc.core.btt_campaign.pojo.BTTCampaignsGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        log.info("Fetched list with status" + status);
        List<BTTCampaignDTO> activeCampaigns = bttCampaignRepository
                .findAllByStatus(status)
                .stream()
                .map(entity -> bttCampaignMapper.toDto(entity, true))
                .peek(dto -> log.info("Dto ->" + dto))
                .collect(Collectors.toList());

        return activeCampaigns;
    }

    public List<BTTCampaignsGroup> separateTriggeredAndFinishedCampaignsIntoTwoGroupsAndDiscardOtherCampaigns(
            List<BTTCampaignDTO> fetchedListFromDB) {

        BTTCampaignsGroup finishedBTTCampaigns = new BTTCampaignsGroup(BTTCampaignStatus.FINISHED, new ArrayList<>());
        BTTCampaignsGroup triggeringBTTCampaigns = new BTTCampaignsGroup(BTTCampaignStatus.TRIGGERING, new ArrayList<>());

        fetchedListFromDB.forEach(bttCampaignDTO -> {
            if (BTTCampaignStatus.evaluateCampaignStatus(bttCampaignDTO) == BTTCampaignStatus.FINISHED)
                finishedBTTCampaigns.getGroup().add(bttCampaignDTO);
            else if (BTTCampaignStatus.evaluateCampaignStatus(bttCampaignDTO) == BTTCampaignStatus.TRIGGERING)
                triggeringBTTCampaigns.getGroup().add(bttCampaignDTO);
        });

        ArrayList<BTTCampaignsGroup> result = new ArrayList<>();

        if (finishedBTTCampaigns.getGroup().size() > 0)
            result.add(finishedBTTCampaigns);

        if (triggeringBTTCampaigns.getGroup().size() > 0)
            result.add(triggeringBTTCampaigns);
        return result;
    }

    public List<BotHubCampaignDTO> shuffleBotHubCampaignsBeforeGettingResultsFromBotHub(List<BTTCampaignDTO> campaigns) {
        ArrayList<BotHubCampaignDTO> result = new ArrayList<BotHubCampaignDTO>();
        int maxNumberOfBotHubCampaigns = 0;

        for (BTTCampaignDTO bttCampaignDTO : campaigns)
            maxNumberOfBotHubCampaigns = Math.max(maxNumberOfBotHubCampaigns, bttCampaignDTO.getBotHubCampaigns().size());

        log.info("campaigns size=" + campaigns.size());

        for (int i = 0; i < maxNumberOfBotHubCampaigns; i++) {
            log.info("i=" + i);
            for (int j = 0; j < campaigns.size(); j++) {
                if (campaigns.get(j).getBotHubCampaigns().size() < maxNumberOfBotHubCampaigns)
                    continue;
                else
                    result.add(campaigns.get(j).getBotHubCampaigns().get(i));
                log.info("j=" + j);
            }
        }
        log.info("Shuffled List " + result);
        return result;
    }

    @Transactional
    public boolean markBTTCampaignWithStatus(BTTCampaignDTO bttCampaignDTO, BTTCampaignStatus status) {
        try {
            bttCampaignRepository.updateBTTCampaignWithStatus(bttCampaignDTO.getId(), status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}