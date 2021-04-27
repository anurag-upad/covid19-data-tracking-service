package com.anurag.covid.dto;

import java.util.List;

public class AlertStatus {

    private String alertLevel;	//RED, ORANGE, GREEN 

    private List<String> measuresToBeTaken;

    private StateData summaryData;
    
    //Getters and Setters start from here
    public StateData getSummaryData() {
        return summaryData;
    }

    public void setSummaryData(StateData summaryData) {
        this.summaryData = summaryData;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public List<String> getMeasuresToBeTaken() {
        return measuresToBeTaken;
    }

    public void setMeasuresToBeTaken(List<String> measuresToBeTaken) {
        this.measuresToBeTaken = measuresToBeTaken;
    }
}
