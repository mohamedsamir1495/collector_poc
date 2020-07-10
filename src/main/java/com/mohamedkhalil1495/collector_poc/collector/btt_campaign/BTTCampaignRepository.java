package com.mohamedkhalil1495.collector_poc.collector.btt_campaign;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BTTCampaignRepository extends CrudRepository<BTTCampaignEntity, Long> {

    List<BTTCampaignEntity> findAllByStatus(int status);
}
