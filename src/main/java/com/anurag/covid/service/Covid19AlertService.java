package com.anurag.covid.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anurag.covid.dto.AlertLevel;
import com.anurag.covid.dto.AlertStatus;
import com.anurag.covid.dto.StateData;
import com.anurag.covid.dto.SummaryData;

@Service
public class Covid19AlertService {
	
	@Autowired
    private Covid19DataProviderService covid19DataProviderService;

    public AlertStatus getCovid19AlertsForState(String state) {

        AlertStatus alertStatus = new AlertStatus();

        //business logic to derive the covid19 alerts
        StateData stateData = covid19DataProviderService.getStateData(state);

        alertStatus.setSummaryData(stateData);
        if (stateData.getTotalConfirmed() < 1000) {
            alertStatus.setAlertLevel(AlertLevel.GREEN.name());
            alertStatus.setMeasuresToBeTaken(Arrays.asList("Everything is Normal !!"));
        }
        else if (stateData.getTotalConfirmed() > 1000 && stateData.getTotalConfirmed() < 10000) {
            alertStatus.setAlertLevel(AlertLevel.ORANGE.name());
            alertStatus.setMeasuresToBeTaken(Arrays.asList("Only Essential services are allowed."));
        }
        else {
            alertStatus.setAlertLevel(AlertLevel.RED.name());
            alertStatus.setMeasuresToBeTaken(Arrays.asList("Absolute lock-down in the state.", "Only Medical and food services are allowed."));
        }

        return alertStatus;
    }

    public SummaryData getOverAllSummary() {

        return covid19DataProviderService.getSummaryData();
    }

}
