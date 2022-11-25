package CodeBenchers006.sb.covid.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import CodeBenchers006.sb.covid.models.LocationStats;





@Service
public class CovidDataService {
	
	
	
	private  String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationStats> allStats = new ArrayList();

	public List<LocationStats> getAllStats() {
		return allStats;
	}

	public void setAllStats(List<LocationStats> allStats) {
		this.allStats = allStats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *") 
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationStats> newStats = new ArrayList();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_DATA_URL))
				.build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStats locationStats = new LocationStats();
		    locationStats.setState(record.get("Province/State"));
		    locationStats.setCountry(record.get("Country/Region"));
		    locationStats.setLatestTotCases(Integer.parseInt(record.get(record.size()-1)));
		    
		    System.out.println(locationStats);
		    newStats.add(locationStats);
		}
		
		this.allStats=newStats;
		
		
	}

}
