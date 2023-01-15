package io.javabrains.coronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.Services;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired
	Services coronaVirusDataService;
	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat->stat.getDifference()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		return "home";
	}

}
