package com.example.Potager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Plantation;

public interface PlantationDAO extends CrudRepository<Plantation, Integer> {

	@Query("select pl from Plantation pl where pl.carre=:c")
	List<Plantation> selectAllPlantationsByCarre(@Param("c") Carre c);

}
