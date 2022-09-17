package com.example.Potager.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;
import com.example.Potager.dal.PlantationDAO;
import com.example.Potager.dal.PlanteDAO;

@Service
public class PlantationManager extends CRUDManager<Plantation, PlantationDAO> {
	@Autowired
	PlantationDAO dao;
	
	public List<Plantation> findAllPlantationsByCarre(Carre c) {
		return dao.findAllPlantationsByCarre(c);
	}
}
