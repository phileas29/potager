package com.example.Potager.ihm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Potager.bll.CarreManager;
import com.example.Potager.bll.PlantationManager;
import com.example.Potager.bll.PlanteManager;
import com.example.Potager.bll.PotagerException;
import com.example.Potager.bll.PotagerManager;
import com.example.Potager.bo.Potager;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	PotagerManager potagerManager;

	@Autowired
	CarreManager carreManager;

	@Autowired
	PlanteManager planteManager;
	
	@Autowired
	PlantationManager plantationManager;
	
	@PostConstruct
	public void init() throws PotagerException {
	}
	
	//accueil
	@GetMapping("")
	public String index(Model model) {
		List<Potager> p = potagerManager.findAll();
		List<Integer> c = potagerManager.countAllCarresByPotager();
		List<Object> l = new ArrayList<Object>();

		Iterator<Potager> pi = p.iterator();
		Iterator<Integer> ci = c.iterator();
	    while (pi.hasNext() && ci.hasNext())
	        l.add(Arrays.asList(pi.next(),ci.next()));
		model.addAttribute("list",l);
		
		return "index";
	}
}
