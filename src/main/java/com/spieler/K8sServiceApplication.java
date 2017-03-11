package com.spieler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCassandraRepositories(basePackages = { "com.spieler.cassandra" })
public class K8sServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8sServiceApplication.class, args);
	}
}
