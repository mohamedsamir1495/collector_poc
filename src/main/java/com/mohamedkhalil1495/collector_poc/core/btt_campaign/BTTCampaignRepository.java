package com.mohamedkhalil1495.collector_poc.core.btt_campaign;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BTTCampaignRepository extends CrudRepository<BTTCampaignEntity, Long> {

    List<BTTCampaignEntity> findAllByStatus(BTTCampaignStatus status);
}