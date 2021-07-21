package com.cea.covidshield.ui.home;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;


import com.cea.covidshield.Api.ApiClient;
import com.cea.covidshield.Api.ApiService;
import com.cea.covidshield.BackGround;
import com.cea.covidshield.R;
import com.cea.covidshield.databinding.FragmentHomeBinding;
import com.cea.covidshield.model.CountryAndVaccine;
import com.cea.covidshield.model.CountryOverview;
import com.cea.covidshield.model.District;
import com.cea.covidshield.model.DistrictDataModel;
import com.cea.covidshield.model.State;
import com.cea.covidshield.model.StateDetailModel;
import com.cea.covidshield.model.VaccineModel;
import com.cea.covidshield.ui.search_vaccine_availablitiy.VaccineAvailabiltyActivity;
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

public class HomeFragment extends Fragment implements View.OnClickListener{

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
    public ApiService apiService_cowin;

    private FragmentHomeBinding binding;

    public CountryAndVaccine CaV;

    // Expanded View
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<CountryAndVaccine>> expandableListDetail;

    List<State> stateList;
    List<String> stateArray;
    List<District> districtList;
    List<String> districtArray;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //

        country_list = new ArrayList<>();
        stateList = new ArrayList<>();
        stateArray = new ArrayList<>();
        districtList = new ArrayList<>();
        districtArray = new ArrayList<>();
        apiService_country = ApiClient.getInstance(getActivity()).getService_Country();
        apiService_vaccine = ApiClient.getInstance(getActivity()).getService_Vaccine();
        apiService_cowin = ApiClient.getInstance(getActivity()).getService_Cowin();
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
                                pieChart.addPieSlice(new PieModel("Confirm",confirm,root.getResources().getColor(R.color.yellow)));
                                pieChart.addPieSlice(new PieModel("Active",active,root.getResources().getColor(R.color.orange)));
                                pieChart.addPieSlice(new PieModel("Recovered",recovered,root.getResources().getColor(R.color.green_pie)));
                                pieChart.addPieSlice(new PieModel("Death",death,root.getResources().getColor(R.color.red_pie)));
                                pieChart.startAnimation();


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

        apiService_cowin.getStates()
                .enqueue(new Callback<StateDetailModel>() {
                    @Override
                    public void onResponse(Call<StateDetailModel> call, Response<StateDetailModel> response) {
                        if (response.body() == null){
                            Toast.makeText(getActivity(),"Error : " + "COWIN API",Toast.LENGTH_SHORT).show();

                        }else {
                            stateList.addAll(response.body().getStates());

                            for(int i=0;i<stateList.size();i++){
                                stateArray.add(stateList.get(i).getState_name());
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<StateDetailModel> call, Throwable t) {
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
                            Intent intent = new Intent(getActivity(), VaccineAvailabiltyActivity.class);
                            intent.putExtra("pincode",pinCodeET.getText().toString().trim());
                            startActivity(intent);
                            bottomSheetDialog.dismiss();
                        }
                    }
                });

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });

        searchByDistrictBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(getActivity(),R.style.BottomSheetTheme);

                LayoutInflater inflater = getLayoutInflater();

                //  View sheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_layout_pin,findViewById(R.id.bottom_sheet_pin));

                View sheetView = inflater.inflate(R.layout.bottom_sheet_layout_district,container ,false);
                AutoCompleteTextView stateActv = sheetView.findViewById(R.id.state_actv);
                AutoCompleteTextView districtActv = sheetView.findViewById(R.id.district_actv);
                ImageView stateDropImgView = sheetView.findViewById(R.id.state_drop_img);
                ImageView districtDropImgView = sheetView.findViewById(R.id.district_drop_img);

                ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,stateArray);
                stateActv.setAdapter(stateAdapter);
                stateActv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        int pos = i;
                        Toast.makeText(getActivity(),stateArray.get(pos),Toast.LENGTH_SHORT).show();
                        Integer state_id = stateList.get(i).getState_id();

                        apiService_cowin.getDistricts(Integer.toString(state_id))
                                .enqueue(new Callback<DistrictDataModel>() {
                                    @Override
                                    public void onResponse(Call<DistrictDataModel> call, Response<DistrictDataModel> response) {
                                        districtList.clear();
                                        districtArray.clear();
                                        districtList.addAll(response.body().getDistricts());
                                        for (int i=0;i<districtList.size();i++){
                                            districtArray.add(districtList.get(i).getDistrictName());
                                        }
                                        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,districtArray);
                                        districtActv.setAdapter(districtAdapter);
                                    }

                                    @Override
                                    public void onFailure(Call<DistrictDataModel> call, Throwable t) {
                                        Toast.makeText(getActivity(),"Error : " + t.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });

                stateDropImgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        stateActv.showDropDown();
                    }
                });

                districtDropImgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        districtActv.showDropDown();
                    }
                });



                sheetView.findViewById(R.id.district_search_btn).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(stateActv.getText().toString()) || TextUtils.isEmpty(districtActv.getText().toString().trim())){
                            Toast.makeText(getActivity(),"Empty Fields!!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Search Button Clicked " + stateActv.getText().toString(),Toast.LENGTH_SHORT).show();
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

        CheckBox notification = binding.notificationBackgroundCheckbox;
        notification.setOnClickListener(this);


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

    @Override
    public void onClick(View view) {

        if (binding.notificationBackgroundCheckbox.isChecked()){

            Intent intent = new Intent(getActivity(), BackGround.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);

            int interval = 1000 * 60;


            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
            Toast.makeText(getContext(), "Notification Set", Toast.LENGTH_SHORT).show();

        }
        else{

            Intent intent = new Intent(getActivity(), BackGround.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

            alarmManager.cancel(pendingIntent);
            Toast.makeText(getContext(), "Notification Canceled", Toast.LENGTH_SHORT).show();


        }

    }
}

