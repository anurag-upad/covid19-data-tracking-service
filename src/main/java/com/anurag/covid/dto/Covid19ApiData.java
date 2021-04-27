package com.anurag.covid.dto;

import java.time.ZonedDateTime;

public class Covid19ApiData {

	private CountryData data;

	private ZonedDateTime lastRefreshedTime;

	private ZonedDateTime lastOriginUpdateTime;
	
	//Getters and Setters start from here
	public CountryData getData() {
		return data;
	}

	public void setData(CountryData data) {
		this.data = data;
	}

	public ZonedDateTime getLastRefreshed() {
		return lastRefreshedTime;
	}

	public void setLastRefreshed(ZonedDateTime lastRefreshed) {
		this.lastRefreshedTime = lastRefreshed;
	}

	public ZonedDateTime getLastOriginUpdate() {
		return lastOriginUpdateTime;
	}

	public void setLastOriginUpdate(ZonedDateTime lastOriginUpdate) {
		this.lastOriginUpdateTime = lastOriginUpdate;
	}
}
