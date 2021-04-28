package com.anurag.covid.dto;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Covid19ApiData {

	private CountryData data;

	private ZonedDateTime lastRefreshed;

	private ZonedDateTime lastOriginUpdate;
	
	private boolean success;
	
	//Getters and Setters created from lombok
}
