package io.yash.coronavirustracker.controllers;

import io.yash.coronavirustracker.models.LocationStats;
import io.yash.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
public class homeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(LocationStats::getLatestTestCases).sum();

        ZoneId zonedId = ZoneId.of("America/Montreal");
        LocalDate today = LocalDate.now(zonedId);

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("date", today);

        return "home";
    }
}
