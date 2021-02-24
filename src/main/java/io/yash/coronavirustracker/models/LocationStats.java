package io.yash.coronavirustracker.models;

public class LocationStats {

    private String Country_Region;
    private String Province_State;
    private Integer latestTestCases;

    public LocationStats() {
    }

    public LocationStats(String country_Region, String province_State, Integer latestTestCases) {
        Country_Region = country_Region;
        Province_State = province_State;
        this.latestTestCases = latestTestCases;
    }

    public String getProvince_State() {
        return Province_State;
    }

    public void setProvince_State(String province_State) {
        this.Province_State = province_State;
    }

    public String getCountry_Region() {
        return Country_Region;
    }

    public void setCountry_Region(String country_Region) {
        this.Country_Region = country_Region;
    }

    public Integer getLatestTestCases() {
        return latestTestCases;
    }

    public void setLatestTestCases(int latestTestCases) {
        this.latestTestCases = latestTestCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "Country_Region='" + Country_Region + '\'' +
                ", Province_State='" + Province_State + '\'' +
                ", latestTestCases=" + latestTestCases +
                '}';
    }
}
