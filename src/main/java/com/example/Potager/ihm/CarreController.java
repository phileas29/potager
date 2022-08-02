package com.example.Potager.ihm;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Potager.bll.CarreManager;
import com.example.Potager.bll.PlantationManager;
import com.example.Potager.bll.PlanteManager;
import com.example.Potager.bll.PotagerException;
import com.example.Potager.bll.PotagerManager;
import com.example.Potager.bo.Carre;
import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Potager;

@Controller
@RequestMapping("/carre")
public class CarreController {
	@Autowired
	PotagerManager potagerManager;

	@Autowired
	CarreManager carreManager;

	@Autowired
	PlanteManager planteManager;
	
	@Autowired
	PlantationManager plantationManager;

	//add carre
	@GetMapping("/addToPotager/{id}")
	public String addCarre(@PathVariable("id") Integer id,Carre carre, Model model) {
		model.addAttribute("typesSol", Arrays.asList(EnumSol.values()));
		model.addAttribute("typesExpo", Arrays.asList(EnumExpo.values()));
		model.addAttribute("potager", potagerManager.findById(id));
		return "carre/add";
	}
	
	//add carre
	@PostMapping("/addToPotager/{id}")
	public String addCarre(@PathVariable("id") Integer id,@Valid Carre carre, BindingResult errors, Model model,RedirectAttributes attributes) {
		Potager potager = potagerManager.findById(id);
		if(errors.hasErrors()) {
			model.addAttribute("typesSol", Arrays.asList(EnumSol.values()));
			model.addAttribute("typesExpo", Arrays.asList(EnumExpo.values()));
			model.addAttribute("potager", potager);
			return "carre/add";
		}
		carre.setPotager(potager);
		try {
			carreManager.add(carre);
		} catch (PotagerException e) {
			attributes.addFlashAttribute("error", e.toString());
			return "redirect:{id}";
		}
		return "redirect:/potager/{id}";
	}
	
	//explorer un carre
	@GetMapping("/{id}")
	public String getCarre(@PathVariable("id") Integer id, Model model) {
		Carre c = carreManager.findById(id);
		Potager potager = c.getPotager();
		List<Plantation> plants = plantationManager.findAllPlantationsByCarre(c);
		model.addAttribute("potager", potager);
		model.addAttribute("carre", c);
		model.addAttribute("plantations", plants);

		return "carre/index";
	}

	//mod carre
	@GetMapping("/mod/{id}")
	public String modCarre(@PathVariable("id") Integer id, Model model) {
		Carre c = carreManager.findById(id);
		model.addAttribute("typesExpo", Arrays.asList(EnumExpo.values()));
		model.addAttribute("typesSol", Arrays.asList(EnumSol.values()));
		model.addAttribute("potager", c.getPotager());
		model.addAttribute("carre", c);
		return "carre/mod";
	}

	//mod carre
	@PostMapping("/mod/{id}")
	public String modCarre(@PathVariable("id") Integer id,@Valid Carre carre, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("typesExpo", Arrays.asList(EnumExpo.values()));
			model.addAttribute("typesSol", Arrays.asList(EnumSol.values()));
			model.addAttribute("potager", carre.getPotager());
			model.addAttribute("carre", carre);
			return "carre/mod";
		}
		try {
			carreManager.add(carre);
		} catch (PotagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/potager/"+carre.getPotager().getIdPotager();
	}
	
	//del carre
	@GetMapping("/del/{id}")
	public String delCarre(@PathVariable("id") Integer id, Model model) {
		Integer idPotager = carreManager.findById(id).getPotager().getIdPotager();
		carreManager.deleteById(id);
		return "redirect:/potager/"+idPotager;
	}
}
