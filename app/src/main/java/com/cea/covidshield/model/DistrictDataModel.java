package com.cea.covidshield.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictDataModel {

    @SerializedName("districts")
    @Expose
    private List<District> districts = null;
    @SerializedName("ttl")
    @Expose
    private Integer ttl;

    /**
     * No args constructor for use in serialization
     *
     */
    public DistrictDataModel() {
    }

    /**
     *
     * @param districts
     * @param ttl
     */
    public DistrictDataModel(List<District> districts, Integer ttl) {
        super();
        this.districts = districts;
        this.ttl = ttl;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

}