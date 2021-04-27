package com.anurag.covid.dto;

public class StateData extends CommonData{
	
	private String loc;
    private int totalConfirmed;
    
    //Getters and Setters start from here
    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

}
