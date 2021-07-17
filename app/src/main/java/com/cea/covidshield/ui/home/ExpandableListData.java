package com.cea.covidshield.ui.home;

import com.cea.covidshield.model.CountryAndVaccine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListData {
    public static HashMap<String, List<CountryAndVaccine>> getData(CountryAndVaccine countryAndVaccine) {



        HashMap<String, List<CountryAndVaccine>> expandableListDetail = new HashMap<String, List<CountryAndVaccine>>();

        List<CountryAndVaccine> CAC = new ArrayList<CountryAndVaccine>();
        CAC.add(countryAndVaccine);

        expandableListDetail.put("CURRENT COVID-19 STATUS", CAC);
        return expandableListDetail;
    }
}
