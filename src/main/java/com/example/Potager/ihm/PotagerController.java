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
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;



@Controller
@RequestMapping("/")
public class PotagerController {
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
		List<Integer> c = carreManager.countAllCarresByPotager();
		List<Object> l = new ArrayList<Object>();

		Iterator<Potager> pi = p.iterator();
		Iterator<Integer> ci = c.iterator();
	    while (pi.hasNext() && ci.hasNext())
	        l.add(Arrays.asList(pi.next(),ci.next()));
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
		
		try {
			potagerManager.add(potager);
		} catch (PotagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}

	//explorer un potager
	@GetMapping("/potager/{id}")
	public String getPotager(@PathVariable("id") Integer id, Model model) {
		Potager potager = potagerManager.findById(id);
		model.addAttribute("potager", potager);

		List<Carre> c = carreManager.findByPotager(potager);
		List<Integer> pl = plantationManager.countAllPlantationsByCarre(potager);
		List<Object> carres = new ArrayList<Object>();

		System.out.println(c);
		System.out.println(pl);
		Iterator<Carre> ci = c.iterator();
		Iterator<Integer> pli = pl.iterator();
	    while (ci.hasNext() && pli.hasNext())
	        carres.add(Arrays.asList(ci.next(),pli.next()));
		model.addAttribute("carres", carres);
		
		System.out.println(carres);
	
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
	@GetMapping("/plantation/addToCarre/{id}")
	public String addPlant(@PathVariable("id") Integer id, Plantation plant , Model model) {
		Carre c = manager.getCarreById(id);
		Potager potager = c.getPotager();
		List<Plante> plantes = manager.findAllPlante();
		plant.setCarre(c);
		model.addAttribute("potager", potager);
		model.addAttribute("carre", c);
		model.addAttribute("plantation", plant);
		model.addAttribute("plantes", plantes);
		return "plantation/add";
	}
	
	//add plant
	@PostMapping("/plantation/addToCarre/{id}")
	public String addPlant(@PathVariable("id") Integer id,@Valid Plantation pla, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			return "plantation/add";
		}
		pla.setCarre(manager.getCarreById(id));
		manager.addPlantation(pla);
		return "redirect:/carre/{id}";
	}
	
	//mod plant
	@GetMapping("/plantation/mod/{id}")
	public String modPlant(@PathVariable("id") Integer id, Model model) {
		Plantation pl = manager.getPlantationById(id);
		Carre c = pl.getCarre();
		model.addAttribute("carre", c);
		model.addAttribute("potager", c.getPotager());
		model.addAttribute("plantation", pl);
		model.addAttribute("plantes", manager.findAllPlante());
		return "plantation/mod";
	}

	//mod plant
	@PostMapping("/plantation/mod/{id}")
	public String modPlant(@PathVariable("id") Integer id,@Valid Plantation pla, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			Carre c = pla.getCarre();
			model.addAttribute("carre", c);
			model.addAttribute("potager", c.getPotager());
			model.addAttribute("plantation", pla);
			model.addAttribute("plantes", manager.findAllPlante());
			return "plantation/mod";
		}
		manager.addPlantation(pla);
		return "redirect:/carre/"+pla.getCarre().getIdCarre();
	}
	
	//del plant
	@GetMapping("/plantation/del/{id}")
	public String delPlant(@PathVariable("id") Integer id, Model model) {
		Integer idCarre = manager.getPlantationById(id).getCarre().getIdCarre();
		manager.delPlantation(id);
		return "redirect:/carre/"+idCarre;
	}
	//////////////FIN (CRUD plantation)
}
