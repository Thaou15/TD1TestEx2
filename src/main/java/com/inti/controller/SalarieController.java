package com.inti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inti.model.Salarie;
import com.inti.repository.ISalarieRepository;
//Class controller

@Controller
public class SalarieController {
	@Autowired
	ISalarieRepository isr;
	
	@GetMapping("ajout")
	public String ajoutSalarie()
	{
		return"formSalarie";
	}
	@PostMapping("save")
	public String saveSalarie(@ModelAttribute ("salarie") Salarie s)
	{
		 isr.save(s);
			
		return "redirect:/ajout";
	}
	
	@GetMapping("listeS")
	public String listeSalarie(Model m)
	{
	    m.addAttribute("listeS",isr.findAll());
		return"listeS";
	}
	
	@GetMapping("getSalarie")
	public String getVoiture(@RequestParam("id") int id, Model m)
	{
		Salarie s = isr.findById(id).get();
		
		m.addAttribute("salarie", s);
		return"afficherSalarie";
	}
	
	@GetMapping("delete/{id}")
	public String deleteSalarie(@PathVariable("id") int id)
	{
		isr.deleteById(id);
		
		return "redirect:/listeS";
	}
	
	@GetMapping("update/{id}")
	public String ModifSalarie(@PathVariable("id") int id, Model m)
	{
		m.addAttribute("salarie",isr.getReferenceById(id));
		
		return "modifSalarie";
	}
	
	@PostMapping("modifS")
	public String updateSalarie(@ModelAttribute ("sal") Salarie s)
	{
		//comme saveOrUpdate
		 isr.save(s);
			
		return "redirect:/listeS";
	}

}
