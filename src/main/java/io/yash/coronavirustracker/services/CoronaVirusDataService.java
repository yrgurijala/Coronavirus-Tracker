package io.yash.coronavirustracker.services;

import io.yash.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String data_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/02-14-2021.csv";
    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron= "* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(data_URL))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader stringReader = new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            locationStats.setCountry_Region(record.get("Country_Region"));
            locationStats.setProvince_State(record.get("Province_State"));
            locationStats.setLatestTestCases(Integer.parseInt(record.get("Confirmed")));

            newStats = checkProvince_State(newStats, locationStats);
        }

        this.allStats = newStats;
    }

    public List<LocationStats> checkProvince_State(List<LocationStats> allStats, LocationStats locationStats){
        for (LocationStats stats: allStats){

            if(locationStats.getProvince_State().equals(stats.getProvince_State()) &&
                    locationStats.getCountry_Region().equals(stats.getCountry_Region())){
                stats.setLatestTestCases(stats.getLatestTestCases() + locationStats.getLatestTestCases());
                return allStats;
            }
        }

        allStats.add(locationStats);
        return allStats;
    }

}
