package com.anurag.covid.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryData {

    private SummaryData summary;

    private List<StateData> regional;
    
    //Getters and Setters created from lombok
    
}
