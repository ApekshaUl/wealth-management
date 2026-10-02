package com.wealthgame.backend;

import com.wealthgame.backend.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WealthManagementApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(WealthManagementApplication.class, args);
//		UserService service1 = context.getBean(UserService.class);
//
//		System.out.println(service1.getClass());
//		service1.createUser();
		AppConfig config = context.getBean(AppConfig.class);
		config.printAppName();


	}

}
