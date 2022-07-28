package com.example.Potager.ihm;


import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Potager.bll.PotagerException;
import com.example.Potager.bll.PotagerManager;
import com.example.Potager.bo.Potager;



@Controller
@RequestMapping("/potager")
public class PotagerController {
	@Autowired
	PotagerManager manager;
	
	@PostConstruct
	public void init() throws PotagerException {
		
		Potager p1 = new Potager("Toul ar C'Hoat", "Clément's Meadows", 800000, "Le Faou");
		Potager p2 = new Potager("Lesquidic", "Jardins Philéasiens", 250000, "Gouesnac'h");
		manager.addPotager(p1);
		manager.addPotager(p2);
		
	}
	
	@GetMapping("")
	public String index(Model model) {

		ArrayList<Integer> c = (ArrayList<Integer>)manager.countCarreByPotager();
		ArrayList<Potager> p = (ArrayList<Potager>)manager.findAllPotager();
		ArrayList<Object> l = new ArrayList<Object>();
		
		for (int i=0;i<p.size();i++) {
			ArrayList<Object> e = new ArrayList<Object>();
			e.add(p.get(i));
			e.add(c.get(i));
			l.add(e);
		}
		model.addAttribute("list",l);
	
		
		return "index";
	}
	
	@GetMapping("/add")
	public String iniAddForm(Potager potager, Model model) {
		return "add";
	}
	
	@PostMapping("/add")
	public String validAddForm(@Valid Potager potager, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "add";
		}
		
		manager.addPotager(potager);
		return "redirect:/potager";
	}
	
	@GetMapping("/del/{id}")
	public String delPotager(@PathVariable("id") Integer id, Model model) {
		manager.delPotagerById(id);
		return "redirect:/potager";
	}
	
	@GetMapping("/mod/{id}")
	public String modPot(@PathVariable("id") Integer id, Model model) {
		Potager potager = manager.getPotagerById(id);
		model.addAttribute("potager", potager);
		return "mod";
	}
	
	@PostMapping("/mod/{id}") 
	public String modeValidPot(@PathVariable("id") Integer id,@Valid Potager potager, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "mod";
		}
		potager.setIdPotager(id);
			manager.addPotager(potager);
	
		return "redirect:/potager";
	}
	
	
	
	
	
	
	
}
