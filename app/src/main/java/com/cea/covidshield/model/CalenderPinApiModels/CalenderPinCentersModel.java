package com.cea.covidshield.model.CalenderPinApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalenderPinCentersModel {

    @SerializedName("centers")
    @Expose
    private List<Center> centers = null;


    public CalenderPinCentersModel() {
    }


    public CalenderPinCentersModel(List<Center> centers) {
        super();
        this.centers = centers;
    }

    public List<Center> getCenters() {
        return centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }

}