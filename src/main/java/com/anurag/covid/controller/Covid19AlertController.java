package com.anurag.covid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.covid.dto.AlertStatus;
import com.anurag.covid.dto.SummaryData;
import com.anurag.covid.service.Covid19AlertService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/india")
public class Covid19AlertController {

    @Autowired
    private Covid19AlertService covid19AlertService;

    @GetMapping("/covidData/{state}")
    @ApiOperation(code = 200, value = "Get Covid19 infected people's data for a particular state in India", notes = " source :www.covid19india.org/ ")
    public AlertStatus covid19AlertsForState(@PathVariable String state){
    	
    	if(!StringUtils.hasText(state)){
			//Handling exception with a custom message using CustomExceptionHandler
			throw new IllegalArgumentException("State field cannot be empty. Please enter a valid state value.");
		}
    	
    	//get Covid19 alerts for a particular state in India; source : https://www.covid19india.org/
        return covid19AlertService.getCovid19AlertsForState(state);
    }

    @GetMapping("/covidData/summary")
    @ApiOperation(code = 200, value = "Get Covid19 infected people's overall summary data for India", notes = " source :www.covid19india.org/ ")
    public SummaryData overAllSummary(){
    	
    	//get Covid19 alerts data summary; source : https://www.covid19india.org/
        return covid19AlertService.getOverAllSummary();
    }
}
