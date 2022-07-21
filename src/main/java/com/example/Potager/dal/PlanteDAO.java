package com.example.Potager.dal;

import org.springframework.data.repository.CrudRepository;

import com.example.Potager.bo.Plante;

public interface PlanteDAO extends CrudRepository<Plante, Integer> {

}
