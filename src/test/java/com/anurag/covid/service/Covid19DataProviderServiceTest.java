package com.anurag.covid.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import com.anurag.covid.dto.CountryData;
import com.anurag.covid.dto.Covid19ApiData;
import com.anurag.covid.dto.StateData;
import com.anurag.covid.dto.SummaryData;

@SpringBootTest
@TestPropertySource(properties = {
	    "covid19.external.api.url=https://api.rootnet.in/covid19-in/stats/latest",
	})
public class Covid19DataProviderServiceTest {
	
	@Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private Covid19DataProviderService covid19DataProvider;
    
    @Value("${covid19.external.api.url}")
    String covid19ApiUrl;

    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("state data provider test")
    void getStateDataTest() {
    	
    	System.out.println(covid19ApiUrl);
        when(restTemplate.getForObject(nullable(String.class), any())).thenReturn(getCovidApiData());

        StateData delhi = covid19DataProvider.getStateData("Delhi");

        assertAll(
                () -> assertEquals("Delhi", delhi.getLoc()),
                () -> assertEquals(4, delhi.getDeaths()),
                () -> assertEquals(0, delhi.getConfirmedCasesForeign()),
                () -> assertEquals(1000, delhi.getConfirmedCasesIndian()),
                () -> assertEquals(4, delhi.getDischarged()),
                () -> assertEquals(1000, delhi.getTotalConfirmed())
        );
    }

    @Test
    @DisplayName("state data provider test - no data found")
    void getStateDataTestNoDataFoundForState() {

        when(restTemplate.getForObject(nullable(String.class), any())).thenReturn(getCovidApiData());

        StateData maharashtra = covid19DataProvider.getStateData("Maharashtra");

        assertAll(
                () -> assertEquals(null, maharashtra.getLoc()),
                () -> assertEquals(0, maharashtra.getDeaths()),
                () -> assertEquals(0, maharashtra.getConfirmedCasesForeign()),
                () -> assertEquals(0, maharashtra.getConfirmedCasesIndian()),
                () -> assertEquals(0, maharashtra.getDischarged()),
                () -> assertEquals(0, maharashtra.getTotalConfirmed())
        );
    }

    @Test
    @DisplayName("summary data test")
    void getSummaryDataTest() {
        when(restTemplate.getForObject(nullable(String.class), any())).thenReturn(getCovidApiDataForSummary());

        SummaryData data = covid19DataProvider.getSummaryData();

        assertAll(
                () -> assertEquals(5, data.getConfirmedButLocationUnidentified()),
                () -> assertEquals(100, data.getTotal()),
                () -> assertEquals(2, data.getDeaths()),
                () -> assertEquals(1, data.getDischarged()),
                () -> assertEquals(10, data.getConfirmedCasesForeign()),
                () -> assertEquals(90, data.getConfirmedCasesIndian()),
                () -> assertNotNull(data.getLastUpdatedTime())
        );
    }

    private Covid19ApiData getCovidApiDataForSummary() {
    	Covid19ApiData covidApiData = new Covid19ApiData();

        CountryData countryData = new CountryData();
        SummaryData summaryData = new SummaryData();
        summaryData.setTotal(100);
        summaryData.setDeaths(2);
        summaryData.setDischarged(1);
        summaryData.setConfirmedCasesIndian(90);
        summaryData.setConfirmedCasesForeign(10);
        summaryData.setLastUpdatedTime(ZonedDateTime.now());
        summaryData.setConfirmedButLocationUnidentified(5);

        countryData.setSummary(summaryData);

        covidApiData.setData(countryData);
        covidApiData.setSuccess(true);
        covidApiData.setLastRefreshed(ZonedDateTime.now());

        return covidApiData;
    }

    private Covid19ApiData getCovidApiData() {
    	Covid19ApiData covidApiData = new Covid19ApiData();

        CountryData countryData = new CountryData();
       /* SummaryData summaryData = new SummaryData();
        summaryData.setTotal(100);
        summaryData.setDeaths(2);
        summaryData.setDischarged(1);
        summaryData.setConfirmedCasesIndian(90);
        summaryData.setConfirmedCasesForeign(10);
        summaryData.setUpdateTime(ZonedDateTime.now());
        countryData.setSummary(summaryData);*/

        StateData sd = new StateData();
        sd.setDeaths(4);
        sd.setLoc("Delhi");
        sd.setDischarged(4);
        sd.setConfirmedCasesIndian(1000);
        sd.setConfirmedCasesForeign(0);
        sd.setTotalConfirmed(1000);
        List<StateData> stateDataList=new ArrayList<StateData>();
        stateDataList.add(sd);
        countryData.setRegional(stateDataList);

        covidApiData.setData(countryData);

        covidApiData.setSuccess(true);
        covidApiData.setLastRefreshed(ZonedDateTime.now());
        return covidApiData;
    }

}
