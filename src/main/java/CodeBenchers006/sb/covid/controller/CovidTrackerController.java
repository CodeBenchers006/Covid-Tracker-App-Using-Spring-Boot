package CodeBenchers006.sb.covid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import CodeBenchers006.sb.covid.models.LocationStats;
import CodeBenchers006.sb.covid.service.CovidDataService;


@Controller
public class CovidTrackerController {
	
	@Autowired
	CovidDataService cserve;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = cserve.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat-> stat.getLatestTotCases()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		return "home";
	}

}
