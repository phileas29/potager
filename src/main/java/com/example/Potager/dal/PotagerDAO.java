package com.example.Potager.dal;

import org.springframework.data.repository.CrudRepository;

import com.example.Potager.bo.Potager;

public interface PotagerDAO extends CrudRepository<Potager, Integer> {

}
