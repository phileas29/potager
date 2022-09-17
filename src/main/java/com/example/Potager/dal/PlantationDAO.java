package com.example.Potager.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Plantation;
import com.example.Potager.bo.Plante;
import com.example.Potager.bo.Potager;

public interface PlantationDAO extends CrudRepository<Plantation, Integer> {
	List<Plantation> findByCarre(Carre c);
	
	@Query("select p from Plantation p join Carre c on c=p.carre where c=:carre")
	List<Plantation> findAllPlantationsByCarre(@Param("carre") Carre c);
}
