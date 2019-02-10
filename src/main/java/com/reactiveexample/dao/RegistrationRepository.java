package com.reactiveexample.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.reactiveexample.entities.Registration;

import reactor.core.publisher.Flux;

public interface RegistrationRepository extends ReactiveMongoRepository<Registration, String> {

	public Flux<List<Registration>>findByEmail(String email ) ;
}
