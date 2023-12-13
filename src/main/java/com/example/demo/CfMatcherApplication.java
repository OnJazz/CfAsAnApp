package com.example.demo;

import com.example.demo.service.ChronoFunctionalService;
import com.example.demo.util.DocxNameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.Optional;

@SpringBootApplication
@EnableScheduling
public class CfMatcherApplication {

	public static final String FOLDER_PATH = "C:\\Users\\jason.vennin_amaris\\Desktop\\test";
	@Autowired
	private ChronoFunctionalService service;

	public static void main(String[] args) {
		SpringApplication.run(CfMatcherApplication.class, args);
	}

	@Scheduled(cron = "0/10 * * * * *") //every 10 seconds
	public void retrieveCf() {
		System.out.println("process has started");
		var file = new File(FOLDER_PATH);
		var fileArray = file.listFiles(new DocxNameFilter());
		Optional.ofNullable(fileArray)
				.ifPresent(elt -> service.uploadFiles(elt));
	}
}
