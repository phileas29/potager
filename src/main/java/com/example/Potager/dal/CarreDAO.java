package com.example.Potager.dal;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Potager;

public interface CarreDAO extends CrudRepository<Carre, Integer> {
	
//	Iterable<Carre> selectAllCarreByPotager(Potager potager);
	
	@Query("select c from Carre c where c.potager=:potager")
	Iterable<Carre> selectAllCarreByPotager(@Param("potager") Potager potager);

}
