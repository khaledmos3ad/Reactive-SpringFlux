package com.khaledeng.reactive.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.khaledeng.reactive.model.User;

import reactor.core.publisher.Mono;

@Service
public class MyService {

	private static final Logger logger = LoggerFactory.getLogger(MyService.class);

	@Async
	public Mono<User> findUser(String username) {
		logger.info("Looking up " + username);
		return Mono.just(new User(1, username));
	}

}
