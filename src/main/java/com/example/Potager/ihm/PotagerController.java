package com.example.Potager.ihm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Potager.bll.PotagerException;
import com.example.Potager.bll.PotagerManager;
import com.example.Potager.bo.Carre;
import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.Potager;



@Controller
@RequestMapping("/potager")
public class PotagerController {
	@Autowired
	PotagerManager manager;
	
	@PostConstruct
	public void init() throws PotagerException {
	}
	
	@GetMapping("")
	public String index(Model model) {
		ArrayList<Integer> c = (ArrayList<Integer>)manager.countCarresByPotager();
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
	public String addPotager(Potager potager, Model model) {
		return "add";
	}
	
	@PostMapping("/add")
	public String addPotager(@Valid Potager potager, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "add";
		}
		
		manager.addPotager(potager);
		return "redirect:/potager";
	}
	
	//add carre
	@GetMapping("/{id}/add")
	public String addCarre(@PathVariable("id") Integer id, Carre carre, Model model) {
		Potager potager = manager.getPotagerById(id);
		model.addAttribute("potager", potager);
		List<EnumExpo> enumExpo = Arrays.asList(EnumExpo.values());
		model.addAttribute("typeExpo", enumExpo);
		List<EnumSol> enumSol = Arrays.asList(EnumSol.values());
		model.addAttribute("typeSol", enumSol);
		return "addCarre";
	}
	
	//add carre
	@PostMapping("/{id}/add")
	public String addCarre(@PathVariable("id") Integer id,@Valid Carre carre, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "/{id}/add";
		}
		Potager potager = manager.getPotagerById(id);
		carre.setPotager(potager);
		manager.addCarreToPotager(potager, carre);
		return "redirect:/potager/{id}";
	}
	
	//del carre
	@GetMapping("/{idP}/{idC}/del")
	public String delCarre(@PathVariable("idC") Integer id, Model model) {
		manager.delCarre(id);
		return "redirect:/potager/{idP}";
	}

	//mod carre
	@GetMapping("/{idP}/{idC}/mod")
	public String modCarre(@PathVariable("idP") Integer idP,@PathVariable("idC") Integer idC, Model model) {
		Carre c = manager.getCarreById(idC);
		Potager potager = manager.getPotagerById(idP);
		model.addAttribute("potager", potager);
		model.addAttribute("carre", c);
		return "modCarre";
	}

	//mod carre
	@PostMapping("/{idP}/{idC}/mod")
	public String modCarre(@PathVariable("idP") Integer idP,@PathVariable("idC") Integer idC,@Valid Carre c, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "modCarre";
		}
		Potager potager = manager.getPotagerById(idP);
		c.setIdCarre(idC);
		c.setPotager(potager);
		manager.addCarreToPotager(potager, c);
		return "redirect:/potager/{idP}";
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
	public String modPot(@PathVariable("id") Integer id,@Valid Potager potager, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "mod";
		}
		potager.setIdPotager(id);
		manager.addPotager(potager);
		return "redirect:/potager";
	}
	
	@GetMapping("/{id}")
	public String getPot(@PathVariable("id") Integer id, Model model) {
		Potager potager = manager.getPotagerById(id);
		model.addAttribute("potager", potager);
		
		ArrayList<Carre> c = (ArrayList<Carre>)manager.selectCarresByPotager(potager);
		ArrayList<Integer> pl = (ArrayList<Integer>)manager.countPlantationsByCarreOfPotager(potager);
		ArrayList<Object> carres = new ArrayList<Object>();
		
		for (int i=0;i<c.size();i++) {
			ArrayList<Object> e = new ArrayList<Object>();
			e.add(c.get(i));
			e.add(pl.get(i));
			carres.add(e);
		}
		model.addAttribute("carres", carres);
	
		return "potager";
	}
	
	
	
	
	
	
	
}
