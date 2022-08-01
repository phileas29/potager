package com.example.Potager.ihm;

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

import com.example.Potager.bll.CarreManager;
import com.example.Potager.bll.PlantationManager;
import com.example.Potager.bll.PlanteManager;
import com.example.Potager.bll.PotagerException;
import com.example.Potager.bll.PotagerManager;
import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;

@Controller
@RequestMapping("/plantation")
public class PlantationController {
	@Autowired
	PotagerManager potagerManager;

	@Autowired
	CarreManager carreManager;

	@Autowired
	PlanteManager planteManager;
	
	@Autowired
	PlantationManager plantationManager;

	//////////////CRUD plantation
	
	//add plant
	@GetMapping("/addToCarre/{id}")
	public String addPlant(@PathVariable("id") Integer id, Plantation plant , Model model) {
		Carre c = carreManager.findById(id);
		Potager potager = c.getPotager();
		List<Plante> plantes = planteManager.findAll();
		plant.setCarre(c);
		model.addAttribute("potager", potager);
		model.addAttribute("carre", c);
		model.addAttribute("plantation", plant);
		model.addAttribute("plantes", plantes);
		return "plantation/add";
	}
	
	//add plant
	@PostMapping("/addToCarre/{id}")
	public String addPlant(@PathVariable("id") Integer id,@Valid Plantation pla, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "plantation/add";
		}
		pla.setCarre(carreManager.findById(id));
		try {
			plantationManager.add(pla);
		} catch (PotagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/carre/{id}";
	}
	
	//mod plant
	@GetMapping("/mod/{id}")
	public String modPlant(@PathVariable("id") Integer id, Model model) {
		Plantation pl = plantationManager.findById(id);
		Carre c = pl.getCarre();
		model.addAttribute("carre", c);
		model.addAttribute("potager", c.getPotager());
		model.addAttribute("plantation", pl);
		model.addAttribute("plantes", planteManager.findAll());
		return "plantation/mod";
	}

	//mod plant
	@PostMapping("/mod/{id}")
	public String modPlant(@PathVariable("id") Integer id,@Valid Plantation pla, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			Carre c = pla.getCarre();
			model.addAttribute("carre", c);
			model.addAttribute("potager", c.getPotager());
			model.addAttribute("plantation", pla);
			model.addAttribute("plantes", planteManager.findAll());
			return "plantation/mod";
		}
		try {
			plantationManager.add(pla);
		} catch (PotagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/carre/"+pla.getCarre().getIdCarre();
	}
	
	//del plant
	@GetMapping("/del/{id}")
	public String delPlant(@PathVariable("id") Integer id, Model model) {
		Integer idCarre = plantationManager.findById(id).getCarre().getIdCarre();
		plantationManager.deleteById(id);
		return "redirect:/carre/"+idCarre;
	}
	//////////////FIN (CRUD plantation)
}
