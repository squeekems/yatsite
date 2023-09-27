package com.squeekems.yat;

import com.squeekems.yat.controllers.EventController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class YatApplication {

	public static void main(String[] args) {
		SpringApplication.run(YatApplication.class, args);
	}

}
