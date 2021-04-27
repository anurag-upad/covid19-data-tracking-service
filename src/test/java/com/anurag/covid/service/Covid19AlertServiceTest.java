package com.anurag.covid.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.anurag.covid.dto.AlertLevel;
import com.anurag.covid.dto.AlertStatus;
import com.anurag.covid.dto.StateData;
import com.anurag.covid.dto.SummaryData;

@SpringBootTest
public class Covid19AlertServiceTest {
	
	@InjectMocks
    private Covid19AlertService alertService;

    @Mock
    private Covid19DataProviderService covid19DataProvider;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When the total number of confirmed cases are less that 1000")
    void getCovid19AlertsForStateTestGreen(){
        StateData stateData = new StateData();
        stateData.setTotalConfirmed(999);

        when(covid19DataProvider.getStateData(ArgumentMatchers.anyString())).thenReturn(stateData);

        AlertStatus status = alertService.getCovid19AlertsForState("GOA");

        assertEquals(AlertLevel.GREEN.name(), status.getAlertLevel());
        assertEquals(Arrays.asList("Everything is Normal !!"), status.getMeasuresToBeTaken());
        assertEquals(stateData, status.getSummaryData());

        verify(covid19DataProvider, Mockito.times(1)).getStateData("GOA");
    }

    @Test
    @DisplayName("When the total number of confirmed cases are less than 10000")
    void getCovid19AlertsForStateTestOrange(){
        StateData stateData = new StateData();
        stateData.setTotalConfirmed(9999);

        when(covid19DataProvider.getStateData(ArgumentMatchers.anyString())).thenReturn(stateData);

        AlertStatus status = alertService.getCovid19AlertsForState("BIHAR");

        assertEquals(AlertLevel.ORANGE.name(), status.getAlertLevel());
        assertEquals(Arrays.asList("Only Essential services are allowed."), status.getMeasuresToBeTaken());
        assertEquals(stateData, status.getSummaryData());

        verify(covid19DataProvider).getStateData("BIHAR");
    }

    @Test
    @DisplayName("Boundary conditions")
    void getCovid19AlertsForStateTestOange2(){
        StateData stateData = new StateData();
        stateData.setTotalConfirmed(1001);

        when(covid19DataProvider.getStateData(ArgumentMatchers.anyString())).thenReturn(stateData);

        AlertStatus status = alertService.getCovid19AlertsForState("RAJASTHAN");

        assertEquals(AlertLevel.ORANGE.name(), status.getAlertLevel());
        assertEquals(Arrays.asList("Only Essential services are allowed."), status.getMeasuresToBeTaken());
        assertEquals(stateData, status.getSummaryData());

        verify(covid19DataProvider).getStateData("RAJASTHAN");
    }
    @Test
    @DisplayName("When the total number of confirmed cases are 10005")
    void getCovid19AlertsForStateTestRed(){
        StateData stateData = new StateData();
        stateData.setTotalConfirmed(10005);

        when(covid19DataProvider.getStateData(ArgumentMatchers.anyString())).thenReturn(stateData);

        AlertStatus status = alertService.getCovid19AlertsForState("DELHI");

        assertEquals(AlertLevel.RED.name(), status.getAlertLevel());
        assertEquals(Arrays.asList("Absolute lock-down in the state.", "Only Medical and food services are allowed."), status.getMeasuresToBeTaken());
        assertEquals(stateData, status.getSummaryData());

        verify(covid19DataProvider).getStateData("DELHI");
    }


    @Test
    @DisplayName("Overall covid summary data test")
    void getOverAllSummaryTest(){
        SummaryData summaryData = new SummaryData();
        summaryData.setLastUpdatedTime(ZonedDateTime.now());
        summaryData.setConfirmedButLocationUnidentified(10);
        summaryData.setConfirmedCasesForeign(1);
        summaryData.setConfirmedCasesIndian(1000);
        summaryData.setDischarged(20);
        summaryData.setDeaths(2);
        summaryData.setTotal(1011);

        when(covid19DataProvider.getSummaryData()).thenReturn(summaryData);

        SummaryData actualSummary = alertService.getOverAllSummary();

        assertEquals(summaryData, actualSummary);

    }

}
