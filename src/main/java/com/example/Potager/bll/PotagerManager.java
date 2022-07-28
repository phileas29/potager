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
	public void addPotager(Potager potager);
	public void delPotagerById(int id);
	public Potager getPotagerById(Integer id);
	public List<Potager> findAllPotager();
	public List<Carre> findAllCarre();
	public List<Plante> findAllPlante();
	public void addPlante(Plante plante);
	public Iterable<Carre> selectCarreByPotager(Potager potager);
	public Iterable<Integer> countCarreByPotager();
	void addPlantation(Plantation pla);
}
