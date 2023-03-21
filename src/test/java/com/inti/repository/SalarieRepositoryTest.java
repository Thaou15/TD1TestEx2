package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Salarie;



@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
public class SalarieRepositoryTest {
	Salarie s;
	Salarie savedSalarie;
	
	@Autowired
	ISalarieRepository isr;
	
	@BeforeEach
	public void setUp()
	{
	    s = new Salarie("Dupès", "Daniel", "dd.da@d.fr") ;
	    savedSalarie = isr.save(s);
	   
	}
	
	@Test
	public void saveTest()
	{
	//Given : dans le setUp
	
	//When
		Salarie savedSalarie = isr.save(s);
	//Then
		assertThat(savedSalarie).isNotNull();
		assertThat(savedSalarie.getId()).isGreaterThan(0);
	}
	
	@Test
	public void getTest()
	{
		//Given : dans le setUp
		   Salarie savedSalarie = isr.save(s);
		
		//When
			Salarie getSalarie = isr.getReferenceById(s.getId());
		//Then
			
			assertThat(getSalarie).isNotNull();
			assertThat(getSalarie.getId()).isEqualTo(savedSalarie.getId());
			assertThat(getSalarie.getNom()).isEqualTo(savedSalarie.getNom());
			assertThat(getSalarie.getPrenom()).isEqualTo(savedSalarie.getPrenom());
			assertThat(getSalarie.getEmail()).isEqualTo(savedSalarie.getEmail());
			assertThat(getSalarie).isEqualTo(savedSalarie);
			
			
	}
	
	@Test
	public void deleteTest()
	{
		//Given : dans le setUp
		
		//When

		isr.delete(savedSalarie);
				
		//Then 
		Assertions.assertThrows(Exception.class,() -> isr.getReferenceById(savedSalarie.getId()));
			
			
	}
	
	@Test
	public void updateTest()
	{
		//When
		savedSalarie.setNom("Durand");
		savedSalarie.setPrenom("Louis");
		savedSalarie.setEmail("info@info.fr");
		
		Salarie salarieModified = isr.save(savedSalarie);
		
		//Then
		assertThat(salarieModified).isNotNull();
		assertThat(salarieModified.getNom()).isEqualTo("Durand");
		assertThat(salarieModified.getPrenom()).isEqualTo("Louis");
		assertThat(salarieModified.getEmail()).isEqualTo("info@info.fr");
		
	}
	
	@Test
	public void getAllSalarie()
	{
		//Given
		Salarie s3 = isr.save(new Salarie("Marie", "Sophie", "test@te.fr"));
		Salarie s4 = isr.save(new Salarie("Durand", "Paul", "tes@te.fr"));
		
		//When
		List<Salarie> listeS = isr.findAll();
		
		// Then
		//Verifie que la liste existe
		assertThat(listeS).isNotEmpty();
		//Verifie que la taille de la liste est de 3
		assertThat(listeS).hasSize(3);
		//On recupère le 1e objet savedSalarie et on verifie qu'il appartient à la classe Salarie
		assertThat(listeS.get(0).getClass()).hasSameClassAs(Salarie.class);
		assertThat(listeS.get(1).toString()).hasToString(s3.toString());
	}
	
	
	

}
