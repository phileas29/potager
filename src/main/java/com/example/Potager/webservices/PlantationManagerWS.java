package com.example.Potager.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Plantation;
import com.example.Potager.dal.CarreDAO;
import com.example.Potager.dal.PlantationDAO;
import com.example.Potager.dal.PotagerDAO;

@RestController
@RequestMapping("api/plantation")
public class PlantationManagerWS extends CRUDManagerWS<Plantation, PlantationDAO> {
	@Autowired
	PlantationDAO dao;
	@Autowired
	CarreDAO carreDao;
	
	@GetMapping("/inCarre/{id}")
	public List<Plantation> findAllPlantationsByCarre(Integer id) {
		return null;
		//return dao.findAllPlantationsByCarre(carreDao.findById(id));
	}
}
