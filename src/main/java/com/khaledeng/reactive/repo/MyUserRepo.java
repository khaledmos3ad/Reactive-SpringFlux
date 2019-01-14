package com.khaledeng.reactive.repo;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.khaledeng.reactive.model.MyUser;

@Repository
public interface MyUserRepo extends ReactiveMongoRepository<MyUser, String> {

}