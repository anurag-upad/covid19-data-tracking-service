package com.anurag.covid.dto;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummaryData extends CommonData{

    private int total;
    private int confirmedButLocationUnidentified;
    private ZonedDateTime lastUpdatedTime;
    
    //Getters and Setters created from lombok

}
