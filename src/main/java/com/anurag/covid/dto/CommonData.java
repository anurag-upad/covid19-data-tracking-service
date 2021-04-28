package com.anurag.covid.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonData {
	
	private int confirmedCasesIndian;
    private int confirmedCasesForeign;
    private int discharged;
    private int deaths;

    //Getters and Setters created from lombok

}
