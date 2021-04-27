package com.anurag.covid.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anurag.covid.dto.Covid19ApiData;
import com.anurag.covid.dto.StateData;
import com.anurag.covid.dto.SummaryData;

@Service
public class Covid19DataProviderService {
	
	@Value("${covid19.external.api.url}")
    private String covid19ApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public StateData getStateData(String state) {

        Covid19ApiData covidApiData = restTemplate.getForObject(covid19ApiUrl, Covid19ApiData.class);

        /*if(!covidApiData.isSuccess()){
            throw new RuntimeException("Issue in fetching data");
        }*/
        return Arrays.stream(covidApiData.getData().getRegional())
	                .filter(e -> e.getLoc().equalsIgnoreCase(state))
	                .findAny()
	                .orElse(new StateData());

    }

    public SummaryData getSummaryData() {
    	
        Covid19ApiData covidApiData = restTemplate.getForObject(covid19ApiUrl, Covid19ApiData.class);

        SummaryData summaryData = covidApiData.getData().getSummary();

        summaryData.setLastUpdatedTime(covidApiData.getLastRefreshed());

        return summaryData;
    }

}
