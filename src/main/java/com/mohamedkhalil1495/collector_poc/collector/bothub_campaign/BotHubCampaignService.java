package com.mohamedkhalil1495.collector_poc.collector.bothub_campaign;

import com.mohamedkhalil1495.collector_poc.collector.bot.BotMapper;
import com.mohamedkhalil1495.collector_poc.collector.bot.BotRepository;
import com.mohamedkhalil1495.collector_poc.collector.btt_campaign.BTTCampaignMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    private final SpringCamelContext springCamelContext;

    public BotHubCampaignService(BotHubCampaignRepository botHubCampaignRepository,
                                 BotRepository botRepository,
                                 BotHubCampaignMapper botHubCampaignMapper,
                                 BTTCampaignMapper bttCampaignMapper,
                                 BotMapper botMapper) {
        this.botHubCampaignRepository = botHubCampaignRepository;
        this.botHubCampaignMapper = botHubCampaignMapper;
        this.botRepository = botRepository;
        this.botMapper = botMapper;
        this.bttCampaignMapper = bttCampaignMapper;
        this.springCamelContext = new SpringCamelContext();
    }

    public List<BotHubCampaignDTO> getAllBotHubCampaignsByStatus(BotHubCampaignStatus status) {
        log.info("{}- Finding {} BotHub Campaign ...", Thread.currentThread().getName(), status);
        List<BotHubCampaignDTO> activeCampaigns = botHubCampaignRepository
                .getAllRunningBotHubCampaignsToCollectTheirBothubResults()
                .stream()
                .map(entity -> botHubCampaignMapper.toSpecialDto(entity,
                        true,
                        false,
                        false)
                )
                .collect(Collectors.toList());

        log.info("{}- Found {} BotHub Campaign ...", Thread.currentThread().getName(), activeCampaigns.size(), status);

        return activeCampaigns;
    }

    @Transactional
    public boolean markBotHubCampaignWithStatus(BotHubCampaignDTO botHubCampaignDTO, BotHubCampaignStatus status) {
        try {
            botHubCampaignRepository.updateBotHubCampaignWithStatus(botHubCampaignDTO.getId(), status);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean saveBotHubCampaignResults(BotHubCampaignDTO botHubCampaignDTO) {

        int totalMsisdns = botHubCampaignDTO.getTotalCount();
        int totalRead = botHubCampaignDTO.getReadCount();
        int totalError = botHubCampaignDTO.getErrorCount();

        if (totalMsisdns == totalRead + totalError)
            botHubCampaignDTO.setStatus(BotHubCampaignStatus.FINISHED);

        try {
            botHubCampaignRepository.updateBotHubCampaignWithBotHubResults(
                    botHubCampaignDTO.getId(),
                    botHubCampaignDTO.getStatus(),
                    botHubCampaignDTO.getSentCount(),
                    botHubCampaignDTO.getDeliveredCount(),
                    botHubCampaignDTO.getReadCount(),
                    botHubCampaignDTO.getErrorCount()
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
