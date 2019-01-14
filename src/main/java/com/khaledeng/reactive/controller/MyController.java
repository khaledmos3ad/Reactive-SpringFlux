package com.khaledeng.reactive.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khaledeng.reactive.model.MyUser;
import com.khaledeng.reactive.model.User;
import com.khaledeng.reactive.repo.MyUserRepo;
import com.khaledeng.reactive.service.MyService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MyController {

	@Autowired
	private MyUserRepo myUserRepo;

	@Autowired
	private MyService myService;
	
	@RequestMapping("/user/{username}")
	public Mono<ResponseEntity<User>> findUser(@PathVariable(value = "username") String username){
		return myService.findUser(username)
                .map(user -> ResponseEntity.ok(user))
                .defaultIfEmpty(ResponseEntity.notFound().build());
	}

    @GetMapping("/users")
    public Flux<MyUser> getAllUsers() {
        return myUserRepo.findAll();
    }

    @PostMapping("/users")
    public Mono<MyUser> createUser(@Valid @RequestBody MyUser user) {
        return myUserRepo.save(user);
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<MyUser>> getUserById(@PathVariable(value = "id") String userId) {
        return myUserRepo.findById(userId)
                .map(savedUser -> ResponseEntity.ok(savedUser))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public Mono<ResponseEntity<MyUser>> updateUser(@PathVariable(value = "id") String userId,
                                                   @Valid @RequestBody MyUser myUser) {
        return myUserRepo.findById(userId)
                .flatMap(existingUser -> {
                    existingUser.setUsername(myUser.getUsername());
                    existingUser.setEmail(myUser.getEmail());
                    return myUserRepo.save(existingUser);
                })
                .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/users/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable(value = "id") String userId) {

        return myUserRepo.findById(userId)
                .flatMap(existingUser ->
                		myUserRepo.delete(existingUser)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
}