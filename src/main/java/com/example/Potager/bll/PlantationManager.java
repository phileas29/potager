package com.example.Potager.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;
import com.example.Potager.dal.PlantationDAO;
import com.example.Potager.dal.PlanteDAO;

@Service
public class PlantationManager extends CRUDManager<Plantation, PlantationDAO> {
	@Autowired
	PlantationDAO dao;
	
//	public void plante(Plante plante, Carre carre, Integer dansNbMois, Integer qte) {
//		dao.save(new Plantation(plante, carre, LocalDate.now(), LocalDate.now().plusMonths(dansNbMois), qte));
//	}

	public List<Plante> planteOfPotager(Potager potager) {
		return dao.findPlantesByPotager(potager);
	}
	
	public List<Integer> countAllPlantationsByCarre(Potager p) {
		return dao.countAllPlantationsByCarre(p);
	}
}
