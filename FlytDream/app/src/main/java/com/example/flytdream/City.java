package com.example.flytdream;

public class City {
    private String cityAlias;
    private String cityName;
    private String cityCountry;

    public City() {
        cityAlias = "";
        cityName = "";
        cityCountry = "";
    }

    public City(String cityAlias, String cityName, String cityCountry) {
        this.cityAlias = cityAlias;
        this.cityName = cityName;
        this.cityCountry = cityCountry;
    }

    public String getCityAlias() {return cityAlias;}
    public String getCityName() {return cityName;}
    public String getCityCountry() {return cityCountry;}
    public String getCityInfo() {
        String cityInfo = String.format("%s, %s, %s", cityAlias, cityName, cityCountry);
        return cityInfo;
    }
}
