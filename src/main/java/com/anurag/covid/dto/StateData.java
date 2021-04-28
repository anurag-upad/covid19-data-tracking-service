package com.anurag.covid.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateData extends CommonData{
	
	private String loc;
    private int totalConfirmed;
    
    //Getters and Setters created from lombok

}
