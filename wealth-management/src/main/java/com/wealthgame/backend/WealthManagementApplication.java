package com.wealthgame.backend;

import com.wealthgame.backend.ioc.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WealthManagementApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(WealthManagementApplication.class, args);
		UserService service1 = context.getBean(UserService.class);
		//UserService service2 = context.getBean(UserService.class);
		//UserRepository repo = service2.repository();
		//repo.saveUser();
		System.out.println(service1.getClass());
		service1.createUser();


	}

}
