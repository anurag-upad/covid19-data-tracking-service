package com.anurag.covid.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class Covid19DataTrackingControllerTest {
	
	@Autowired
    private MockMvc mockMvc;


	@Test
	@DisplayName("Integration test which invokes REST endpoints in Controller and external REST endpoint to fetch state specific covid19 data")
    void getCovidAlertsForStateTest() throws Exception {

        mockMvc.perform(get("/india/covidData/delhi"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Integration test which invokes REST endpoints in Controller and external REST endpoint to fetch combined summary covid19 data")
    void getOverAllSummaryTest() throws Exception {

        mockMvc.perform(get("/india/covidData/summary"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Integration test which invokes an invalid REST endpoint")
    void invalidEndpoint() throws Exception {

        mockMvc.perform(get("/india/123"))
                .andExpect(status().isNotFound());
    }
}
