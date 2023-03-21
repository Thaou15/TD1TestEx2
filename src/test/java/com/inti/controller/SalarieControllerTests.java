package com.inti.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.inti.model.Salarie;
import com.inti.repository.ISalarieRepository;

//On veut tester la class SalarieController
@WebMvcTest(controllers = SalarieController.class)
public class SalarieControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ISalarieRepository isr;
	
	@Test
	@DisplayName("Test d'inscription d'un salarie")
	public void ajoutSalarie() throws Exception
	{
		try {
			mockMvc.perform(get("/ajout"))
			.andExpect(status().isOk())
			.andDo(print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test d'enregistrement d'un salarie")
	public void enregistrementSalarie() throws Exception
	{
		try {
			mockMvc.perform(post("/save").sessionAttr("salarie", new Salarie("Durant", "Dan", "test@te.fr")))
			.andExpect(status().is3xxRedirection())
			.andDo(print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test da'affichage de tous les salariés")
	public void getallSalarie() throws Exception
	{
		try {
			mockMvc.perform(get("/listeS"))
			.andExpect(status().isOk())
			.andExpect(view().name("listeS"))
			//On verifie que dans la page affiché dans le navigateur on a une chaine de caractère qui correspond à : liste des salariés
			.andExpect((ResultMatcher) content().string(containsString("Liste des salariés")))
			.andDo(print());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test suppression d'un salarie")
	public void deleteSalarie() throws Exception
	{
		try {
			mockMvc.perform(get("/delete/1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/listeS"))
			.andDo(print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
