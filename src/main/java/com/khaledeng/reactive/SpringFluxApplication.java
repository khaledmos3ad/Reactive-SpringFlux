package com.khaledeng.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.khaledeng.reactive.repo.MyUserRepo;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages= {"com.khaledeng.reactive.repo"})
public class SpringFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFluxApplication.class, args);
	}

}

