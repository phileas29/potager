package com.example.Potager.bll;

import java.util.List;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;

public interface PotagerManager {
	
	public void addCarreToPotager(Potager potager, int surface, EnumSol typeSol, EnumExpo typeExpo);
	public void addCarreToPotager(Potager potager, Carre carre);
	public void addPotager(Potager potager);
	public void delCarre(int id);
	public void delPotagerById(int id);
	public Potager getPotagerById(Integer id);
	public List<Potager> findAllPotager();
	public List<Carre> findAllCarre();
	public List<Plante> findAllPlante();
	public void addPlante(Plante plante);
	public Iterable<Carre> selectCarresByPotager(Potager p);
	public Iterable<Plantation> selectAllPlantationsByCarre(Carre c);
	public Iterable<Integer> countCarresByPotager();
	public Iterable<Integer> countPlantationsByCarreOfPotager(Potager p);
	void addPlantation(Plantation pla);
	public Carre getCarreById(Integer id);
}
