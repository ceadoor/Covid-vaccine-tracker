package com.cea.covidshield.Api;

import com.cea.covidshield.model.CalenderPinApiModels.CalenderPinCentersModel;
import com.cea.covidshield.model.CountryOverview;
import com.cea.covidshield.model.DistrictDataModel;
import com.cea.covidshield.model.StateDetailModel;
import com.cea.covidshield.model.VaccineModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @GET("v2/countries")
    Call<List<CountryOverview>> getCountryData();

    @GET("vaccine/vaccine_counts_today.json")
    Call<VaccineModel> getVaccineDosesinfo(@Query("timestamp") String timestamp);

    @GET("v2/admin/location/states")
    Call<StateDetailModel> getStates();

    @GET("v2/admin/location/districts/{state_id}")
    Call<DistrictDataModel> getDistricts(@Path(value = "state_id",encoded = true) String state_id);

    @GET("v2/appointment/sessions/public/calendarByPin")
    Call<CalenderPinCentersModel> getCentreWeekByPin(@Query("pincode") String pincode,@Query("date") String date);

}

