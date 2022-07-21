package com.example.Potager.bll;

import java.util.List;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.EnumExpo;
import com.example.Potager.bo.EnumSol;
import com.example.Potager.bo.Potager;

public interface PotagerManager {
	
	public void addCarreToPotager(Potager potager, int surface, EnumSol typeSol, EnumExpo typeExpo);
	public void addPotager(Potager potager);
	public List<Potager> findAllPotager();
	public List<Carre> finAllCarre();
	
}
