package com.reactiveexample.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reactiveexample.entities.Registration;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationRepositoryTest {
	
	@Autowired
	private RegistrationRepository regRepo ;
	
	private List<Registration>  registrationList = Arrays.asList( 
			
			new Registration("anoop@gmail.com","Anoop" ,"lal") ,
			new Registration("lachu@gmail.com","Lachu" ,"Anoop") ,
			new Registration("lachu1@gmail.com","Lachu1" ,"Anoop1") 
			
			) ;
	
	@Before
	public void setUp() throws Exception {
		
		regRepo.deleteAll().thenMany(Flux.fromIterable(registrationList)).flatMap(regRepo::save).then().block();
	}
	
	@Test
	public void save() {
		
		Registration registration = new Registration("niti@gmail.com","Nitika" ,"Anooplal") ;
		
		StepVerifier.create(regRepo.save(registration)).expectNextMatches(reg -> !"".equals(reg.getId()) ).verifyComplete();
	}
	
	@Test
	public void findAll() {

		StepVerifier.create(regRepo.findAll()).expectNextCount(3).verifyComplete();
	}

}
