package com.example.Potager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;

public interface PlantationDAO extends CrudRepository<Plantation, Integer> {

//	@Query("select pl from Plantation pl where pl.carre=:c")
//	List<Plantation> getAllPlantationsOfCarre(@Param("c") Carre c);
//	
//	@Query("SELECT p.plante FROM Plantation p WHERE p.carre.potager=:potager")
//	List<Plante> getAllPlantesOfPotager(@Param("potager") Potager potager);
	List<Plantation> findByCarre(Carre c);
	
	@Query("select count(c) from Carre c group by potager")
	List<Integer> countAllCarresByPotager();
	
	@Query("select count(p) from Plantation p group by carre")
	List<Integer> countAllPlantationsByCarre(@Param("potager") Potager potager);
	
	@Query("SELECT p.plante FROM Plantation p WHERE p.carre.potager=:potager")
	List<Plante> findPlantesByPotager(@Param("potager") Potager potager);

}
