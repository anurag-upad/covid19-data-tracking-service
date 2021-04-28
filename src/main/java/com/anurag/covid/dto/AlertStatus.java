package com.anurag.covid.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertStatus {

    private String alertLevel;	//RED, ORANGE, GREEN 

    private List<String> measuresToBeTaken;

    private StateData summaryData;
    
    //Getters and Setters created from lombok
    
}
