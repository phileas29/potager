package com.example.Potager.dal;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Potager;

public interface CarreDAO extends CrudRepository<Carre, Integer> {
	
	List<Carre> findByPotager(Potager potager);
	
	@Query("select count(c) from Carre c group by potager")
	List<Integer> countAllCarresByPotager();
	
//	@Query("select c from Carre c where c.potager=:potager")
//	List<Carre> selectAllCarresByPotager(@Param("potager") Potager potager);

}
