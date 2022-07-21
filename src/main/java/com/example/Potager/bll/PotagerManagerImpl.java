package com.example.Potager.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.Potager;
import com.example.Potager.bo.Carre;
import com.example.Potager.dal.CarreDAO;
import com.example.Potager.dal.PotagerDAO;


@Service
public class PotagerManagerImpl implements PotagerManager {
	
	@Autowired
	PotagerDAO pDao;
	
	@Autowired
	CarreDAO cDao;

	@Override
	public void addCarreToPotager(Potager potager, int surface, EnumSol typeSol, EnumExpo typeExpo) {
		Carre carre = new Carre(surface, typeSol, typeExpo);
		carre.setPotager(potager);
		cDao.save(carre);		
	}

	@Override
	public void addPotager(Potager potager) {
		pDao.save(potager);		
	}

	@Override
	public List<Potager> findAllPotager() {
		return (List<Potager>) pDao.findAll();
	}

	@Override
	public List<Carre> finAllCarre() {
		return (List<Carre>) cDao.findAll();
	}
	
	

}
