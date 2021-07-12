package com.cea.covidshield.Api;

import com.cea.covidshield.model.CountryOverview;
import com.cea.covidshield.model.VaccineModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface ApiService {

    @GET("v2/countries")
    Call<List<CountryOverview>> getCountryData();

    @GET("vaccine/vaccine_counts_today.json")
    Call<VaccineModel> getVaccineDosesinfo(@Query("timestamp") String timestamp);

}

