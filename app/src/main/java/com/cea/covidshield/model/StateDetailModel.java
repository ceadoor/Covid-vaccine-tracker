package com.cea.covidshield.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateDetailModel {

    @SerializedName("states")
    private List<State> states;

    public StateDetailModel(List<State> states) {
        this.states = states;
    }

    public StateDetailModel() {
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }


}