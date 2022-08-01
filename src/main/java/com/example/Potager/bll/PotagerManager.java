package com.example.Potager.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Potager.bo.Potager;
import com.example.Potager.dal.PotagerDAO;


@Service
public class PotagerManager extends CRUDManager<Potager, PotagerDAO> {
	
	public List<Integer> countAllCarresByPotager() {
		return dao.countAllCarresByPotager();
	}

}
