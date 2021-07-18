package com.cea.covidshield.ui.search_vaccine_availablitiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.cea.covidshield.Api.ApiClient;
import com.cea.covidshield.Api.ApiService;
import com.cea.covidshield.R;
import com.cea.covidshield.model.CalenderPinApiModels.CalenderPinCentersModel;
import com.cea.covidshield.model.CalenderPinApiModels.Center;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineAvailabiltyActivity extends AppCompatActivity {
    private List<Center> centreList;
    private RecyclerView centresRecyclerView;
    public ApiService apiService_cowin;
    VaccineRecyclerViewAdapter vaccineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_availabilty);

        Intent intent = getIntent();
        String pincode = intent.getExtras().getString("pincode");
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());



        centreList = new ArrayList<>();
        apiService_cowin = ApiClient.getInstance(VaccineAvailabiltyActivity.this).getService_Cowin();

        centresRecyclerView = (RecyclerView) findViewById(R.id.centers_recyclerview);
        centresRecyclerView.setHasFixedSize(true);
        centresRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        apiService_cowin.getCentreWeekByPin(pincode,date)
                .enqueue(new Callback<CalenderPinCentersModel>() {
                    @Override
                    public void onResponse(Call<CalenderPinCentersModel> call, Response<CalenderPinCentersModel> response) {
                        centreList.clear();
                        centreList.addAll(response.body().getCenters());


                        vaccineAdapter = new VaccineRecyclerViewAdapter(centreList);
                        centresRecyclerView.setAdapter(vaccineAdapter);

                    }

                    @Override
                    public void onFailure(Call<CalenderPinCentersModel> call, Throwable t) {

                    }
                });




    }
}