package io.yash.coronavirustracker.services;


import io.yash.coronavirustracker.models.LocationStats;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class CoronaVirusDataServiceTest {

    List<LocationStats> allStats = new ArrayList<>();
    CoronaVirusDataService dataService = new CoronaVirusDataService();

    @Test
    public void checkProvince_StateTest() throws IOException, InterruptedException {
        allStats.add(new LocationStats("US", "Texas", 200));

        List<LocationStats> newStats = dataService.checkProvince_State(allStats,
                new LocationStats("US", "Texas", 300));

        assertThat(newStats.get(0).getLatestTestCases(), is(500));
    }

}
