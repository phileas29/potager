package com.example.Potager.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;
import com.example.Potager.bo.Carre;
import com.example.Potager.dal.CarreDAO;
import com.example.Potager.dal.PlantationDAO;
import com.example.Potager.dal.PlanteDAO;
import com.example.Potager.dal.PotagerDAO;


@Service
public class PotagerManagerImpl implements PotagerManager {
	
	@Autowired
	PotagerDAO pDao;
	
	@Autowired
	CarreDAO cDao;
	
	@Autowired
	PlanteDAO plDao;
	
	@Autowired
	PlantationDAO plaDao;

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
	public void addPlantation(Plantation pla) {
		plaDao.save(pla);		
	}

	@Override
	public List<Potager> findAllPotager() {
		return (List<Potager>) pDao.findAll();
	}

	@Override
	public List<Carre> findAllCarre() {
		return (List<Carre>) cDao.findAll();
	}

	@Override
	public List<Plante> findAllPlante() {
		return (List<Plante>) plDao.findAll();
	}

	@Override
	public void addPlante(Plante plante) {
		plDao.save(plante);		
	}

	@Override
	public List<Carre> selectCarresByPotager(Potager p) {
		return (List<Carre>) cDao.selectAllCarresByPotager(p);
	}

	@Override
	public List<Integer> countCarresByPotager() {
		List<Integer> listInt = new ArrayList<Integer>();
		List<Potager> list = (List<Potager>) pDao.findAll();
		for (Potager potager2 : list) {
			listInt.add(((List<Carre>)cDao.selectAllCarresByPotager(potager2)).size());
		}
		
		return listInt;
	}

	@Override
	public void delPotagerById(int id) {
		pDao.deleteById(id);
	}

	@Override
	public Potager getPotagerById(Integer id) {
		
		return pDao.findById(id).orElse(null);
	}

	@Override
	public void addCarreToPotager(Potager potager, Carre carre) {
		carre.setPotager(potager);
		cDao.save(carre);		
	}

	@Override
	public void delCarre(int id) {
		cDao.deleteById(id);
	}

	@Override
	public Iterable<Integer> countPlantationsByCarreOfPotager(Potager p) {
		List<Integer> listInt = new ArrayList<Integer>();
		List<Carre> list = (List<Carre>) cDao.selectAllCarresByPotager(p);
		for (Carre c : list) {
			listInt.add(((List<Plantation>)plaDao.selectAllPlantationsByCarre(c)).size());
		}
		
		return listInt;
	}

	@Override
	public Iterable<Plantation> selectAllPlantationsByCarre(Carre c) {
		return (List<Plantation>) plaDao.selectAllPlantationsByCarre(c);
	}

	@Override
	public Carre getCarreById(Integer id) {
		
		return cDao.findById(id).orElse(null);
	}
}
