package com.cea.covidshield.model;

public class VaccineModel {

    private String india_dose1;
    private String india_dose2;

    public String getIndia_dose1() {
        return india_dose1;
    }

    public void setIndia_dose1(String india_dose1) {
        this.india_dose1 = india_dose1;
    }

    public String getIndia_dose2() {
        return india_dose2;
    }

    public void setIndia_dose2(String india_dose2) {
        this.india_dose2 = india_dose2;
    }

    public VaccineModel(String india_dose1, String india_dose2) {
        this.india_dose1 = india_dose1;
        this.india_dose2 = india_dose2;
    }
}
