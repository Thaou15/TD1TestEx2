package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Entreprise;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EntrepriseRepositoryTest {
	
	Entreprise e;
	
	@Autowired
	IEntrepriseRepository ier;
	
	@BeforeEach
	public void setUp()
	{
		e = new Entreprise("Coca-cola", "25 av district, L.A");
	}
	
	@Test
	public void saveTest()
	{
	//Given : dans le setUp
	
	//When
		Entreprise savedEntreprise = ier.save(e);
	//Then
		assertThat(savedEntreprise).isNotNull();
		assertThat(savedEntreprise.getId()).isGreaterThan(0);
	}
	
	

}
