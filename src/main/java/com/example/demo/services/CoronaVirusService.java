package com.example.demo.services;

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
import org.apache.poi.hpsf.Array;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.models.LocationStats;
@Service
public class CoronaVirusService {
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStats> allStats=new ArrayList<>();
	private int total=0;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *") // permet cette methode de s'executer chaque jour 
    public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStats> newStats=new ArrayList<>();
    	HttpClient client = HttpClient.newHttpClient();
    	HttpRequest request = HttpRequest.newBuilder()
    			.uri(URI.create(VIRUS_DATA_URL))
    			.build();
    	HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    	StringReader csvBodyReader = new StringReader(httpResponse.body());
    	// il va iterer sur la premiere ligne comme un header
    	Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader); 
    	for (CSVRecord record : records) {
    		LocationStats locationStats=new LocationStats();
    		locationStats.setState(record.get("Province/State"));
    		locationStats.setContry(record.get("Country/Region"));
    		locationStats.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
    		total=total+Integer.parseInt(record.get(record.size()-1));

    		System.out.println(locationStats);
    		newStats.add(locationStats);
    	}
    	this.allStats=newStats;
    	  
    }
	public List<LocationStats> getAllStats() {
		return allStats;
	}
	public void setAllStats(List<LocationStats> allStats) {
		this.allStats = allStats;
	}
}
