package com.anurag.covid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anurag.covid.dto.Covid19ApiData;
import com.anurag.covid.dto.StateData;
import com.anurag.covid.dto.SummaryData;
import com.anurag.covid.exception.ServerDownException;

@Service
public class Covid19DataProviderService {
	
	@Value("${covid19.external.api.url}")
    private String covid19ApiUrl;

    @Autowired
    private RestTemplate restTemplate;
    

    public StateData getStateData(String state) {
    	
    	Covid19ApiData covidApiData = null;
    	try {
    		covidApiData = restTemplate.getForObject(covid19ApiUrl, Covid19ApiData.class);
    	}catch(Exception e) {
    		throw new ServerDownException("External REST endpoint for getting Covid19 data is down. Please try again after some time.");
    	}

        if(covidApiData != null && !covidApiData.isSuccess()){
        	throw new ServerDownException("Covid19 Data couldn't be fetched. Please try again after some time.");
        }
        
        return	covidApiData.getData().getRegional()
        			.stream()
	                .filter(e -> e.getLoc().equalsIgnoreCase(state.trim()))
	                .findAny()
	                .orElse(new StateData());

    }

    public SummaryData getSummaryData() {
    	
    	Covid19ApiData covidApiData = null;
    	try {
    		covidApiData = restTemplate.getForObject(covid19ApiUrl, Covid19ApiData.class);
    	}catch(Exception e) {
    		throw new ServerDownException("External REST endpoint for getting Covid19 data is down. Please try again after some time.");
    	}

        if(covidApiData != null && !covidApiData.isSuccess()){
        	throw new ServerDownException("Covid19 Data couldn't be fetched. Please try again after some time.");
        }
    	
        SummaryData summaryData = covidApiData.getData().getSummary();

        summaryData.setLastUpdatedTime(covidApiData.getLastRefreshed());

        return summaryData;
    }

}
