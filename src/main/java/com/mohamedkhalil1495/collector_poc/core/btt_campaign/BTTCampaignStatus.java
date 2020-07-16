package com.mohamedkhalil1495.collector_poc.core.btt_campaign;

import com.mohamedkhalil1495.collector_poc.core.bot.BotDTO;
import com.mohamedkhalil1495.collector_poc.core.bot.BotStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public enum BTTCampaignStatus {
    NOT_STARTED, RUNNING, TRIGGERING, FINISHED, STOPPED, BOT_DEACTIVATED, BOT_ERROR;

    public static BTTCampaignStatus evaluateCampaignStatus(BTTCampaignDTO bttCampaignDTO) {
        BTTCampaignStatus campaignStatus = bttCampaignDTO.getStatus();

        BotDTO campaignBot = bttCampaignDTO.getBot();

        if (campaignBot.getStatus() == BotStatus.ERROR)
            return BTTCampaignStatus.BOT_ERROR;

        if (campaignBot.getStatus() == BotStatus.DEACTIVATED)
            return BTTCampaignStatus.BOT_DEACTIVATED;

        if (isBTTCampaignFinished(bttCampaignDTO))
            return BTTCampaignStatus.FINISHED;

        if (bttCampaignDTO.getStatus() == BTTCampaignStatus.STOPPED)
            return BTTCampaignStatus.STOPPED;

        if (isBTTCampaignNotStarted(bttCampaignDTO))
            return BTTCampaignStatus.NOT_STARTED;

        if (isBTTCampaignTriggering(bttCampaignDTO))
            return BTTCampaignStatus.TRIGGERING;

        if (isBTTCampaignRunning(bttCampaignDTO))
            return BTTCampaignStatus.RUNNING;

        throw new IllegalStateException("BTT Status unknown from its current data");
    }

    private static boolean isBTTCampaignFinished(BTTCampaignDTO bttCampaignDTO) {
        int total = bttCampaignDTO.getTotalMsisdns();
        int read = bttCampaignDTO.getTotalRead();
        int error = bttCampaignDTO.getTotalError();

        LocalDate dateTo = bttCampaignDTO.getEndDate();
        LocalTime timeTo = bttCampaignDTO.getTriggerEndTime();

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        boolean isCurrentDateGreaterThanDateTo = currentDate.isAfter(dateTo);
        boolean isCurrentDateEqualToDateTo = currentDate.isEqual(dateTo);
        boolean isCurrentTimeGreaterThanTimeTo = currentTime.isAfter(timeTo);

        return total == read + error || total == error || isCurrentDateGreaterThanDateTo || (isCurrentDateEqualToDateTo && isCurrentTimeGreaterThanTimeTo);
    }

    private static boolean isBTTCampaignNotStarted(BTTCampaignDTO bttCampaignDTO) {
        LocalDate dateFrom = bttCampaignDTO.getStartDate();

        LocalTime timeFrom = bttCampaignDTO.getTriggerStartTime();

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        boolean isCurrentDateGreaterThanOrEqualDateFrom = currentDate.isAfter(dateFrom) || currentDate.isEqual(dateFrom);
        boolean isCurrentDateGreaterThanDateFrom = currentDate.isAfter(dateFrom);

        boolean isCurrentTimeGreaterThanTimeFrom = currentTime.isAfter(timeFrom);

        boolean campaignDidStart = isCurrentDateGreaterThanDateFrom || (isCurrentDateGreaterThanOrEqualDateFrom && isCurrentTimeGreaterThanTimeFrom);

        return !campaignDidStart;
    }

    private static boolean isBTTCampaignTriggering(BTTCampaignDTO bttCampaignDTO) {

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        LocalDate dateFrom = bttCampaignDTO.getStartDate();
        LocalDate dateTo = bttCampaignDTO.getEndDate();

        LocalTime timeFrom = bttCampaignDTO.getTriggerStartTime();
        LocalTime timeTo = bttCampaignDTO.getTriggerEndTime();


        boolean isCurrentDateGreaterThanOrEqualDateFrom = currentDate.isAfter(dateFrom) || currentDate.isEqual(dateFrom);
        boolean isCurrentDateLessThanOrEqualDateTo = currentDate.isBefore(dateTo) || currentDate.isEqual(dateTo);
        boolean isCurrentTimeGreaterThanOrEqualTimeFrom = currentTime.isAfter(timeFrom) || (!currentTime.isBefore(timeFrom));
        boolean isCurrentTimeLessThanOrEqualTimeTo = currentTime.isBefore(timeTo) || (!currentTime.isAfter(timeTo));

        return isCurrentDateGreaterThanOrEqualDateFrom && isCurrentDateLessThanOrEqualDateTo && isCurrentTimeGreaterThanOrEqualTimeFrom && isCurrentTimeLessThanOrEqualTimeTo;
    }

    private static boolean isBTTCampaignRunning(BTTCampaignDTO bttCampaignDTO) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        LocalDate dateFrom = bttCampaignDTO.getStartDate();
        LocalDate dateTo = bttCampaignDTO.getEndDate();

        LocalTime timeFrom = bttCampaignDTO.getTriggerStartTime();
        LocalTime timeTo = bttCampaignDTO.getTriggerEndTime();

        boolean isCurrentDateGreaterThanDateFrom = currentDate.isAfter(dateFrom);
        boolean isCurrentDateLessThanDateTo = currentDate.isBefore(dateTo);

        boolean isCurrentDateEqualDateFrom = currentDate.isEqual(dateFrom);
        boolean isCurrentDateEqualDateTo = currentDate.isEqual(dateTo);

        boolean isCurrentTimeGreaterThanOrEqualTimeFrom = currentTime.isAfter(timeFrom) || (!currentTime.isBefore(timeFrom));
        boolean isCurrentTimeLessThanOrEqualTimeTo = currentTime.isBefore(timeTo) || (!currentTime.isAfter(timeTo));

        return (isCurrentDateGreaterThanDateFrom && isCurrentDateLessThanDateTo) ||
                (isCurrentDateEqualDateFrom && isCurrentTimeGreaterThanOrEqualTimeFrom) ||
                (isCurrentDateEqualDateTo && isCurrentTimeLessThanOrEqualTimeTo);

    }

}