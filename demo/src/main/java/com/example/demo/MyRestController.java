package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	@GetMapping("/checkWeather")
	public WeatherReport getWeather() {
		
		WeatherReport report = new WeatherReport();
		report.setHeadlineText("ヘッドラインです");
		report.setPublishingOffice("野崎天文台");
		report.setTargetArea("オランダ");
		report.setText("本日は晴天なり");	
		
		return report;
	}
	
	
	@GetMapping("/checkWeather/{areaCode}")
	public WeatherReport getWeather(@PathVariable String areaCode) {
		
		WeatherReport report = new WeatherReport();
		
		report.setHeadlineText("ヘッドラインです");
		report.setPublishingOffice("野崎天文台");
		report.setTargetArea("areaCodeは" + areaCode + "です。");
		report.setText("本日は晴天なり");	
		
		return report;
	}
	
	
	@PostMapping("/weather")
	public WeatherReport postWeather(@RequestParam String areaCode) {
		
		WeatherReport report = new WeatherReport();
		
		report.setHeadlineText("ヘッドラインです");
		report.setPublishingOffice("野崎天文台");
		report.setTargetArea("areaCodeは" + areaCode + "です。");
		report.setText("本日は晴天なり");	
		
		return report;
	}	
}