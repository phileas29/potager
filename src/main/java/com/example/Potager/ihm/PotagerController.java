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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Potager.bll.PotagerException;
import com.example.Potager.bll.PotagerManager;
import com.example.Potager.bo.Carre;
import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;



@Controller
@RequestMapping("/")
public class PotagerController {
	@Autowired
	PotagerManager manager;
	
	@PostConstruct
	public void init() throws PotagerException {
	}
	
	//accueil
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
	
	//////////////CRUD potager
	@GetMapping("/potager/add")
	public String addPotager(Potager potager, Model model) {
		return "potager/add";
	}
	
	@PostMapping("/potager/add")
	public String addPotager(@Valid Potager potager, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "potager/add";
		}
		
		manager.addPotager(potager);
		return "redirect:/";
	}

	//explorer un potager
	@GetMapping("/potager/{id}")
	public String getPotager(@PathVariable("id") Integer id, Model model) {
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
	
		return "potager/index";
	}

	//modifier potager
	@GetMapping("/potager/mod/{id}")
	public String modPotager(@PathVariable("id") Integer id, Potager potager, Model model) {
		potager = manager.getPotagerById(id);
		model.addAttribute("potager", potager);
		return "potager/mod";
	}
	
	//modifier potager
	@PostMapping("/potager/mod/{id}")
	public String modPotager(@PathVariable("id") Integer id,@Valid Potager potager, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "potager/mod";
		}
		manager.addPotager(potager);
		return "redirect:/";
	}

	//supprimer potager
	@GetMapping("/potager/del/{id}")
	public String delPotager(@PathVariable("id") Integer id, Model model) {
		manager.delPotagerById(id);
		return "redirect:/";
	}
	//////////////FIN (CRUD potager)

	//////////////CRUD carre
	//add carre
	@GetMapping("/carre/addToPotager/{id}")
	public String addCarre(@PathVariable("id") Integer id,Carre carre, Model model) {
		model.addAttribute("typesExpo", Arrays.asList(EnumExpo.values()));
		model.addAttribute("typesSol", Arrays.asList(EnumSol.values()));
		model.addAttribute("potager", manager.getPotagerById(id));
		return "carre/add";
	}
	
	//add carre
	@PostMapping("/carre/addToPotager/{id}")
	public String addCarre(@PathVariable("id") Integer id,@Valid Carre carre, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("typesExpo", Arrays.asList(EnumExpo.values()));
			model.addAttribute("typesSol", Arrays.asList(EnumSol.values()));
			model.addAttribute("potager", manager.getPotagerById(id));
			return "carre/add";
		}
		Potager potager = manager.getPotagerById(id);
		carre.setPotager(potager);
		manager.addCarreToPotager(potager, carre);
		return "redirect:/potager/{id}";
	}
	
	//explorer un carre
	@GetMapping("/carre/{id}")
	public String getCarre(@PathVariable("id") Integer id, Model model) {
		Carre c = manager.getCarreById(id);
		Potager potager = c.getPotager();
		List<Plantation> plants = manager.selectAllPlantationsByCarre(c);
		model.addAttribute("potager", potager);
		model.addAttribute("carre", c);
		model.addAttribute("plantations", plants);
	
		return "carre/index";
	}

	//mod carre
	@GetMapping("/carre/mod/{id}")
	public String modCarre(@PathVariable("id") Integer id, Model model) {
		Carre c = manager.getCarreById(id);
		model.addAttribute("typesExpo", Arrays.asList(EnumExpo.values()));
		model.addAttribute("typesSol", Arrays.asList(EnumSol.values()));
		model.addAttribute("potager", c.getPotager());
		model.addAttribute("carre", c);
		return "carre/mod";
	}

	//mod carre
	@PostMapping("/carre/mod/{id}")
	public String modCarre(@PathVariable("id") Integer id,@Valid Carre carre, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("typesExpo", Arrays.asList(EnumExpo.values()));
			model.addAttribute("typesSol", Arrays.asList(EnumSol.values()));
			model.addAttribute("potager", carre.getPotager());
			model.addAttribute("carre", carre);
			return "carre/mod";
		}
		manager.addCarreToPotager(carre.getPotager(), carre);
		return "redirect:/potager/"+carre.getPotager().getIdPotager();
	}
	
	//del carre
	@GetMapping("/carre/del/{id}")
	public String delCarre(@PathVariable("id") Integer id, Model model) {
		Integer idPotager = manager.getCarreById(id).getPotager().getIdPotager();
		manager.delCarre(id);
		return "redirect:/potager/"+idPotager;
	}
	//////////////FIN (CRUD carre)

	//////////////CRUD plantation
	
	//add plant
//	@GetMapping("/plantation/{idP}/{idC}/add")
	@GetMapping("/plantation/add/")
	public String addPlant(@PathVariable("idP") Integer idP,@PathVariable("idC") Integer idC, Plantation plant , Model model) {
		Carre c = manager.getCarreById(idC);
		Potager potager = manager.getPotagerById(idP);
		List<Plante> plantes = manager.findAllPlante();
		model.addAttribute("potager", potager);
		model.addAttribute("carre", c);
		model.addAttribute("plantation", plant);
		model.addAttribute("plantes", plantes);
		return "addPlant";
	}
	
	//add plant
	@PostMapping("/plantation/{idP}/{idC}/add")
	public String addPlant(@PathVariable("idP") Integer idP,@PathVariable("idC") Integer idC,@Valid Plantation pla, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "redirect:/potager/{idP}/{idC}";
		}
		Carre c = manager.getCarreById(idC);
		pla.setCarre(c);
		manager.addPlantation(pla);
		return "redirect:/potager/{idP}/{idC}";
	}
	
	//mod plant
	@GetMapping("/plantation/{idP}/{idC}/{idPl}/mod")
	public String modPlant(@PathVariable("idP") Integer idP,@PathVariable("idC") Integer idC,@PathVariable("idPl") Integer idPl, Model model) {
		Plantation pl = manager.getPlantationById(idPl);
		Potager potager = manager.getPotagerById(idP);
		Carre c = manager.getCarreById(idC);
		List<Plante> plantes = manager.findAllPlante();
		model.addAttribute("potager", potager);
		model.addAttribute("carre", c);
		model.addAttribute("plantation", pl);
		model.addAttribute("plantes", plantes);
		return "modPlant";
	}

	//mod plant
	@PostMapping("/plantation/{idP}/{idC}/{idPl}/mod")
	public String modPlant(@PathVariable("idP") Integer idP,@PathVariable("idC") Integer idC,@PathVariable("idPl") Integer idPl,@Valid Plantation pla, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "modPlant";
		}
		Carre c = manager.getCarreById(idC);
		pla.setCarre(c);
		pla.setIdPlantation(idPl);
		manager.addPlantation(pla);
		return "redirect:/potager/{idP}/{idC}";
	}
	
	//del plant
	@GetMapping("/plantation/{idP}/{idC}/{idPl}/del")
	public String delPlant(@PathVariable("idPl") Integer id, Model model) {
		manager.delPlantation(id);
		return "redirect:/potager/{idP}/{idC}";
	}
	//////////////FIN (CRUD plantation)
}
