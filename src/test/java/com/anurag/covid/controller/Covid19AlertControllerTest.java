package com.anurag.covid.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.anurag.covid.dto.AlertLevel;
import com.anurag.covid.dto.AlertStatus;
import com.anurag.covid.dto.SummaryData;
import com.anurag.covid.service.Covid19AlertService;

@SpringBootTest
@AutoConfigureMockMvc
public class Covid19AlertControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private Covid19AlertService alertService;


    @Test
    void getCovidAlertsForStateTest() throws Exception {

        AlertStatus alertStatus = new AlertStatus();
        alertStatus.setAlertLevel(AlertLevel.RED.name());
        Mockito.when(alertService.getCovid19AlertsForState(ArgumentMatchers.anyString())).thenReturn(alertStatus);

        mockMvc.perform(get("/india/covidData/delhi"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"alertLevel\":\"RED\",\"measuresToBeTaken\":null,\"summaryData\":null}"));
    }

    @Test
    void getOverAllSummaryTest() throws Exception {

        SummaryData sd  = new SummaryData();
        sd.setTotal(1000000);

        Mockito.when(alertService.getOverAllSummary()).thenReturn(sd);

        mockMvc.perform(get("/india/covidData/summary"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"total\":1000000,\"confirmedCasesIndian\":0,\"confirmedCasesForeign\":0,\"discharged\":0,\"deaths\":0,\"confirmedButLocationUnidentified\":0}"));
    }

    @Test
    void invalidEndpoint() throws Exception {

        mockMvc.perform(get("/india/123"))
                .andExpect(status().isNotFound());
    }
}
