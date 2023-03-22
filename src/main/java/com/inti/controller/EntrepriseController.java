package com.inti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.model.Entreprise;

import com.inti.repository.IEntrepriseRepository;

@Controller
public class EntrepriseController {
	
	@Autowired
	IEntrepriseRepository ier;
	
	@GetMapping("ajoutE")
	public String ajoutEntreprise()
	{
		return "formE";
	}
	
	@PostMapping("save")
	public String saveEntreprise(@ModelAttribute ("entreprise") Entreprise e)
	{
		 ier.save(e);
			
		return "redirect:/ajoutE";
	}
	

}
