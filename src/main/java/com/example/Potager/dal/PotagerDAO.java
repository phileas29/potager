package com.example.Potager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Potager.bo.Carre;
import com.example.Potager.bo.Potager;

public interface PotagerDAO extends CrudRepository<Potager, Integer> {
	@Query("select count(c) from Potager p left join Carre c on c.potager=p group by p")
	List<Integer> countAllCarresByPotager();
}
