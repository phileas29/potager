package com.example.Potager.dal;

import org.springframework.data.repository.CrudRepository;

import com.example.Potager.bo.Plantation;

public interface PlantationDAO extends CrudRepository<Plantation, Integer> {

}
