package com.cea.covidshield.model.CalenderPinApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Session {

    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("available_capacity")
    @Expose
    private int availableCapacity;
    @SerializedName("min_age_limit")
    @Expose
    private int minAgeLimit;
    @SerializedName("allow_all_age")
    @Expose
    private boolean allowAllAge;
    @SerializedName("vaccine")
    @Expose
    private String vaccine;
    @SerializedName("slots")
    @Expose
    private List<String> slots = null;
    @SerializedName("available_capacity_dose1")
    @Expose
    private int availableCapacityDose1;
    @SerializedName("available_capacity_dose2")
    @Expose
    private int availableCapacityDose2;
    @SerializedName("max_age_limit")
    @Expose
    private int maxAgeLimit;

    /**
     * No args constructor for use in serialization
     *
     */
    public Session() {
    }

    /**
     *
     * @param date
     * @param maxAgeLimit
     * @param availableCapacity
     * @param minAgeLimit
     * @param availableCapacityDose1
     * @param vaccine
     * @param slots
     * @param availableCapacityDose2
     * @param sessionId
     * @param allowAllAge
     */
    public Session(String sessionId, String date, int availableCapacity, int minAgeLimit, boolean allowAllAge, String vaccine, List<String> slots, int availableCapacityDose1, int availableCapacityDose2, int maxAgeLimit) {
        super();
        this.sessionId = sessionId;
        this.date = date;
        this.availableCapacity = availableCapacity;
        this.minAgeLimit = minAgeLimit;
        this.allowAllAge = allowAllAge;
        this.vaccine = vaccine;
        this.slots = slots;
        this.availableCapacityDose1 = availableCapacityDose1;
        this.availableCapacityDose2 = availableCapacityDose2;
        this.maxAgeLimit = maxAgeLimit;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public int getMinAgeLimit() {
        return minAgeLimit;
    }

    public void setMinAgeLimit(int minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }

    public boolean isAllowAllAge() {
        return allowAllAge;
    }

    public void setAllowAllAge(boolean allowAllAge) {
        this.allowAllAge = allowAllAge;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    public int getAvailableCapacityDose1() {
        return availableCapacityDose1;
    }

    public void setAvailableCapacityDose1(int availableCapacityDose1) {
        this.availableCapacityDose1 = availableCapacityDose1;
    }

    public int getAvailableCapacityDose2() {
        return availableCapacityDose2;
    }

    public void setAvailableCapacityDose2(int availableCapacityDose2) {
        this.availableCapacityDose2 = availableCapacityDose2;
    }

    public int getMaxAgeLimit() {
        return maxAgeLimit;
    }

    public void setMaxAgeLimit(int maxAgeLimit) {
        this.maxAgeLimit = maxAgeLimit;
    }

}