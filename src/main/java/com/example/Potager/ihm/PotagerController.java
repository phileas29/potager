package com.example.Potager.ihm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Potager.bll.CarreManager;
import com.example.Potager.bll.PlantationManager;
import com.example.Potager.bll.PlanteManager;
import com.example.Potager.bll.PotagerException;
import com.example.Potager.bll.PotagerManager;
import com.example.Potager.bo.Carre;
import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;



@Controller
@RequestMapping("/potager")
public class PotagerController {
	@Autowired
	PotagerManager potagerManager;

	@Autowired
	CarreManager carreManager;
	
	@PostConstruct
	public void init() throws PotagerException {
	}
	
	@GetMapping("/add")
	public String addPotager(Potager potager, Model model) {
		return "potager/add";
	}
	
	@PostMapping("/add")
	public String addPotager(@Valid Potager potager, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "potager/add";
		}
		
		try {
			potagerManager.add(potager);
		} catch (PotagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}

	//explorer un potager
	@GetMapping("/{id}")
	public String getPotager(@PathVariable("id") Integer id, Model model) {
		Potager potager = potagerManager.findById(id);
		model.addAttribute("potager", potager);

		List<Carre> c = carreManager.findByPotager(potager);
		List<Integer> pl = carreManager.countAllPlantationsByCarre(potager);
		List<Object> carres = new ArrayList<Object>();
		
		Iterator<Carre> ci = c.iterator();
		Iterator<Integer> pli = pl.iterator();
	    while (ci.hasNext() && pli.hasNext())
	        carres.add(Arrays.asList(ci.next(),pli.next()));
		model.addAttribute("carres", carres);
	
		return "potager/index";
	}

	//modifier potager
	@GetMapping("/mod/{id}")
	public String modPotager(@PathVariable("id") Integer id, Potager potager, Model model) {
		potager = potagerManager.findById(id);
		model.addAttribute("potager", potager);
		return "potager/mod";
	}
	
	//modifier potager
	@PostMapping("/mod/{id}")
	public String modPotager(@PathVariable("id") Integer id,@Valid Potager potager, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "potager/mod";
		}
		try {
			potagerManager.add(potager);
		} catch (PotagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}

	//supprimer potager
	@GetMapping("/del/{id}")
	public String delPotager(@PathVariable("id") Integer id, Model model) {
		potagerManager.deleteById(id);
		return "redirect:/";
	}
}
