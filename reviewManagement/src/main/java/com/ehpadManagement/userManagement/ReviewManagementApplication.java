package com.ehpadManagement.userManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaServer
public class ReviewManagementApplication {

	// Création de la SessionFactory
	public static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {

		SpringApplication.run(ReviewManagementApplication.class, args);
		//test addUser
		/*UserService userService=new UserService();
		User user=new User("Damso");
		userService.deleteUser(8);
		System.out.println("------Fin-------");*/
	}

}
