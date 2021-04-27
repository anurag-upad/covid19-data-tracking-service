package com.anurag.covid.dto;

import java.time.ZonedDateTime;

public class SummaryData extends CommonData{

    private int total;
    private int confirmedButLocationUnidentified;
    private ZonedDateTime lastUpdatedTime;
    
    //Getters and Setters start from here
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getConfirmedButLocationUnidentified() {
        return confirmedButLocationUnidentified;
    }

    public void setConfirmedButLocationUnidentified(int confirmedButLocationUnidentified) {
        this.confirmedButLocationUnidentified = confirmedButLocationUnidentified;
    }

	public ZonedDateTime getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(ZonedDateTime lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

}
