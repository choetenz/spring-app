package com.tenzin.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:SpringJPA.xml"})
@ComponentScan("com.tenzin.app")
public class SampleApplication {
	
	public static void main(String[] args) throws Exception{
		
		SpringApplication.run(SampleApplication.class, args);
		//ConfigurableApplicationContext context = 
	}
	
}
