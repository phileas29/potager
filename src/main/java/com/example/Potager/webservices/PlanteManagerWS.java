package com.example.Potager.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Potager.bo.Plante;
import com.example.Potager.dal.PlanteDAO;
import com.example.Potager.dal.PotagerDAO;

@RestController
@RequestMapping("api/plante")
public class PlanteManagerWS extends CRUDManagerWS<Plante, PlanteDAO> {
	@Autowired
	PotagerDAO dao;
}
