package com.anurag.covid.dto;

public class CountryData {

    private SummaryData summary;

    private StateData[] regional;
    
    //Getters and Setters start from here
    public SummaryData getSummary() {
        return summary;
    }

    public void setSummary(SummaryData summary) {
        this.summary = summary;
    }

    public StateData[] getRegional() {
        return regional;
    }

    public void setRegional(StateData[] regional) {
        this.regional = regional;
    }
}
