package com.ps.wikicoordinator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WikiCoordinatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WikiCoordinatorApplication.class, args);
	}

}
