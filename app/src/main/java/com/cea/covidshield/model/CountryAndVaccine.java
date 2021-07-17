package com.cea.covidshield.model;

public class CountryAndVaccine {

    private CountryOverview countryOverview;
    private VaccineModel vaccineModel;

    public CountryAndVaccine() {
    }

    public CountryAndVaccine(CountryOverview countryOverview, VaccineModel vaccineModel) {
        this.countryOverview = countryOverview;
        this.vaccineModel = vaccineModel;
    }

    public CountryOverview getCountryOverview() {
        return countryOverview;
    }

    public void setCountryOverview(CountryOverview countryOverview) {
        this.countryOverview = countryOverview;
    }

    public VaccineModel getVaccineModel() {
        return vaccineModel;
    }

    public void setVaccineModel(VaccineModel vaccineModel) {
        this.vaccineModel = vaccineModel;
    }
}
