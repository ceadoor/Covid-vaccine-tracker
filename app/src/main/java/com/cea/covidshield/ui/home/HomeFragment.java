package com.cea.covidshield.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;


import com.cea.covidshield.Api.ApiClient;
import com.cea.covidshield.Api.ApiService;
import com.cea.covidshield.R;
import com.cea.covidshield.databinding.FragmentHomeBinding;
import com.cea.covidshield.model.CountryAndVaccine;
import com.cea.covidshield.model.CountryOverview;
import com.cea.covidshield.model.VaccineModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{

    private TextView totalConfirm;
    private TextView totalActive;
    private TextView totalRecovered;
    private TextView totalDeath;
    private TextView totalTests;
    private TextView todayConfirm;
    private TextView todayRecovered;
    private TextView todayDeath;
    private TextView dose1_tv;
    private TextView dose2_tv;
    private TextView updatedate;
    private PieChart pieChart;
    private AppCompatButton searchByPinBtn;
    private AppCompatButton searchByDistrictBtn;
    private BottomSheetDialog bottomSheetDialog;

    private List<CountryOverview> country_list;
    private VaccineModel vaccineData;
    public ApiService apiService_country;
    public ApiService apiService_vaccine;

    private FragmentHomeBinding binding;

    public CountryAndVaccine CaV;

    // Expanded View
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<CountryAndVaccine>> expandableListDetail;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //

        country_list = new ArrayList<>();
        apiService_country = ApiClient.getInstance(getActivity()).getService_Country();
        apiService_vaccine = ApiClient.getInstance(getActivity()).getService_Vaccine();
        init();
        CaV = new CountryAndVaccine();

        apiService_country.getCountryData()
                .enqueue(new Callback<List<CountryOverview>>() {
                    @Override
                    public void onResponse(Call<List<CountryOverview>> call, Response<List<CountryOverview>> response) {
                        country_list.addAll(response.body());

                        for (int i=0; i<country_list.size();i++){
                            if (country_list.get(i).getCountry().equals("India")){

                                CaV.setCountryOverview(country_list.get(i));

                                int confirm = Integer.parseInt(country_list.get(i).getCases());
                                int active = Integer.parseInt(country_list.get(i).getActive());
                                int recovered = Integer.parseInt(country_list.get(i).getRecovered());
                                int death = Integer.parseInt(country_list.get(i).getDeaths());

                                setUpdateDate(country_list.get(i).getUpdated());
                                pieChart.addPieSlice(new PieModel("Confirm",confirm,getResources().getColor(R.color.yellow)));
                                pieChart.addPieSlice(new PieModel("Active",active,getResources().getColor(R.color.orange)));
                                pieChart.addPieSlice(new PieModel("Recovered",recovered,getResources().getColor(R.color.green_pie)));
                                pieChart.addPieSlice(new PieModel("Death",death,getResources().getColor(R.color.red_pie)));



                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CountryOverview>> call, Throwable t) {
                        Toast.makeText(getActivity(),"Error : " + t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        String timestamp = Long.toString(System.currentTimeMillis()/1000L);

        apiService_vaccine.getVaccineDosesinfo(timestamp)
                .enqueue(new Callback<VaccineModel>() {
                    @Override
                    public void onResponse(Call<VaccineModel> call, Response<VaccineModel> response) {

                        vaccineData = response.body();

                        CaV.setVaccineModel(vaccineData);


                    }

                    @Override
                    public void onFailure(Call<VaccineModel> call, Throwable t) {
                        Toast.makeText(getActivity(),"Error : " + t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

        searchByPinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(getActivity(),R.style.BottomSheetTheme);

                LayoutInflater inflater = getLayoutInflater();

              //  View sheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_layout_pin,findViewById(R.id.bottom_sheet_pin));

                View sheetView = inflater.inflate(R.layout.bottom_sheet_layout_pin,container ,false);
                EditText pinCodeET = sheetView.findViewById(R.id.pic_code_edittext);
                sheetView.findViewById(R.id.pin_search_btn).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(pinCodeET.getText().toString())){
                            Toast.makeText(getActivity(),"Please Enter PIN Code",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Search Button Clicked " + pinCodeET.getText().toString(),Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                        }
                    }
                });

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });

//        Toast.makeText(getActivity(), "CaV " + CaV.getCountryOverview().getActive()+CaV.getVaccineModel().getIndia_dose1(), Toast.LENGTH_SHORT).show();

        expandableListView = binding.expandableListView;
        expandableListDetail = ExpandableListData.getData(CaV);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        //



        return root;
    }

    private void setUpdateDate(String updated) {
        DateFormat format =  new SimpleDateFormat("MMM dd, yyyy");

        long milliseconds = Long.parseLong(updated);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        updatedate.setText("Updated at "+format.format(calendar.getTime()));
    }

    private void init(){

        pieChart = binding.pieChart;
        updatedate = binding.updateDate;

        searchByPinBtn = binding.searchByPinBtn;
        searchByDistrictBtn = binding.searchByDistrictBtn;

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
