package com.anurag.covid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.covid.dto.AlertStatus;
import com.anurag.covid.dto.SummaryData;
import com.anurag.covid.service.Covid19AlertService;

@RestController
@RequestMapping("/india")
public class Covid19AlertController {

    @Autowired
    private Covid19AlertService covid19AlertService;

    @GetMapping("/covidData/{state}")
    public AlertStatus covid19AlertsForState(@PathVariable String state){
    	//get Covid19 alerts for a particular state in India
    	//source : https://www.covid19india.org/
        return covid19AlertService.getCovid19AlertsForState(state);
    }

    @GetMapping("/covidData/summary")
    public SummaryData overAllSummary(){
    	//get Covid19 alerts data summary
    	//source : https://www.covid19india.org/
        return covid19AlertService.getOverAllSummary();
    }
}
