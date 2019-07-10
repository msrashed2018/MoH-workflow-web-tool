package com.almostkbal.web.services.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.almostkbal.web.services.workflow.services.StorageService;

@SpringBootApplication
public class MoHRestfulApplication implements ApplicationRunner{
	
	@Autowired
	private StorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(MoHRestfulApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		storageService.deleteAll();
		storageService.init();
		
	}
}
