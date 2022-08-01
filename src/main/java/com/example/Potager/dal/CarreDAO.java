package com.example.Potager.dal;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Potager;

public interface CarreDAO extends CrudRepository<Carre, Integer> {
	List<Carre> findByPotager(Potager potager);
	
	@Query("select count(p) from Carre c left join Plantation p on p.carre=c join Potager po on po=c.potager where po=:potager group by c")
	List<Integer> countAllPlantationsByCarre(@Param("potager") Potager potager);
}
