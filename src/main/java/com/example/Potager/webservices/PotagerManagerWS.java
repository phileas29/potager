package com.example.Potager.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Potager.bo.Potager;
import com.example.Potager.dal.PotagerDAO;

@RestController
@RequestMapping("api/potager")
public class PotagerManagerWS extends CRUDManagerWS<Potager, PotagerDAO> {
	@Autowired
	PotagerDAO dao;
	
	@GetMapping("/nbCarres")
	public List<Integer> countAllCarresByPotager() {
		return dao.countAllCarresByPotager();
	}
}
