package com.ehpadManagement.userManagement;

import com.ehpadManagement.userManagement.model.User;
import com.ehpadManagement.userManagement.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UserManagementApplication {

	// Création de la SessionFactory
	public static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


	public static void main(String[] args) {

		SpringApplication.run(UserManagementApplication.class, args);

	}

}
