package com.cea.covidshield.model.CalenderPinApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VaccineFee {

    @SerializedName("vaccine")
    @Expose
    private String vaccine;
    @SerializedName("fee")
    @Expose
    private String fee;

    /**
     * No args constructor for use in serialization
     *
     */
    public VaccineFee() {
    }

    /**
     *
     * @param vaccine
     * @param fee
     */
    public VaccineFee(String vaccine, String fee) {
        super();
        this.vaccine = vaccine;
        this.fee = fee;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

}