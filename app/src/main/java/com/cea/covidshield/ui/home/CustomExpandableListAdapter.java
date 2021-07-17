package com.cea.covidshield.ui.home;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cea.covidshield.R;
import com.cea.covidshield.model.CountryAndVaccine;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<CountryAndVaccine>> expandableListDetail;


    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<CountryAndVaccine>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final CountryAndVaccine countryAndVaccine = (CountryAndVaccine) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item,null);
        }

        TextView totalConfirm = (TextView) convertView
                .findViewById((R.id.totalConfirm));
        TextView totalActive = (TextView) convertView
                .findViewById(R.id.totalActive);
        TextView totalDeath = (TextView) convertView
                .findViewById((R.id.totalDeath));
        TextView totalRecovered = (TextView) convertView
                .findViewById((R.id.totalRecovered));
        TextView totalTests = (TextView) convertView
                .findViewById((R.id.totalTests));
        TextView todayConfirm = (TextView) convertView
                .findViewById((R.id.todayConfirm));
        TextView todayRecovered = (TextView) convertView
                .findViewById((R.id.todayRecovered));
        TextView todayDeath = (TextView) convertView
                .findViewById((R.id.todayDeath));
        TextView dose1_tv = (TextView) convertView
                .findViewById(R.id.dose1);
        TextView dose2_tv = (TextView) convertView
                .findViewById(R.id.dose2);

        int confirm = Integer.parseInt(countryAndVaccine.getCountryOverview().getCases());
        int active = Integer.parseInt(countryAndVaccine.getCountryOverview().getActive());
        int recovered = Integer.parseInt(countryAndVaccine.getCountryOverview().getRecovered());
        int death = Integer.parseInt(countryAndVaccine.getCountryOverview().getDeaths());


        totalActive.setText(NumberFormat.getInstance().format(active));
        totalConfirm.setText(NumberFormat.getInstance().format(confirm));
        totalRecovered.setText(NumberFormat.getInstance().format(recovered));
        totalDeath.setText(NumberFormat.getInstance().format(death));

        todayDeath.setText(NumberFormat.getInstance().format(Integer.parseInt(countryAndVaccine.getCountryOverview().getTodayDeaths())));
        todayConfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(countryAndVaccine.getCountryOverview().getTodayCases())));
        todayRecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(countryAndVaccine.getCountryOverview().getTodayRecovered())));
        totalTests.setText(NumberFormat.getInstance().format(Integer.parseInt(countryAndVaccine.getCountryOverview().getTests())));

        dose1_tv.setText(NumberFormat.getInstance().format(Integer.parseInt(countryAndVaccine.getVaccineModel().getIndia_dose1())));
        dose2_tv.setText(NumberFormat.getInstance().format(Integer.parseInt(countryAndVaccine.getVaccineModel().getIndia_dose2())));


        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}