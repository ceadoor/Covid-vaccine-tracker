package com.cea.covidshield.model.CalenderPinApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Center {

    @SerializedName("center_id")
    @Expose
    private int centerId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("block_name")
    @Expose
    private String blockName;
    @SerializedName("pincode")
    @Expose
    private int pincode;
    @SerializedName("lat")
    @Expose
    private int lat;
    @SerializedName("long")
    @Expose
    private int _long;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("fee_type")
    @Expose
    private String feeType;
    @SerializedName("sessions")
    @Expose
    private List<Session> sessions = null;
    @SerializedName("vaccine_fees")
    @Expose
    private List<VaccineFee> vaccineFees = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Center() {
    }

    /**
     *
     * @param centerId
     * @param pincode
     * @param sessions
     * @param address
     * @param districtName
     * @param blockName
     * @param feeType
     * @param stateName
     * @param _long
     * @param name
     * @param from
     * @param to
     * @param lat
     * @param vaccineFees
     */
    public Center(int centerId, String name, String address, String stateName, String districtName, String blockName, int pincode, int lat, int _long, String from, String to, String feeType, List<Session> sessions, List<VaccineFee> vaccineFees) {
        super();
        this.centerId = centerId;
        this.name = name;
        this.address = address;
        this.stateName = stateName;
        this.districtName = districtName;
        this.blockName = blockName;
        this.pincode = pincode;
        this.lat = lat;
        this._long = _long;
        this.from = from;
        this.to = to;
        this.feeType = feeType;
        this.sessions = sessions;
        this.vaccineFees = vaccineFees;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLong() {
        return _long;
    }

    public void setLong(int _long) {
        this._long = _long;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<VaccineFee> getVaccineFees() {
        return vaccineFees;
    }

    public void setVaccineFees(List<VaccineFee> vaccineFees) {
        this.vaccineFees = vaccineFees;
    }

}
