package com.example.rms_microservice.task;

import com.example.rms_microservice.service.DailySettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailySettlementTask {

    @Autowired
    private DailySettlementService dailySettlementService;

    @Scheduled(cron = "0 0 * * * ?") // 1 hour interval
    public void runDailySettlementTask() {
        dailySettlementService.calculateAndSaveDailySettlements();
    }
}

