package com.example.Potager.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Potager.bo.Carre;
import com.example.Potager.dal.CarreDAO;
import com.example.Potager.dal.PotagerDAO;

@RestController
@RequestMapping("api/carre")
public class CarreManagerWS extends CRUDManagerWS<Carre, CarreDAO> {
	@Autowired
	CarreDAO dao;
	
//	dao.
}
