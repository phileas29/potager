package com.example.Potager.bll;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public abstract class CRUDManager<E,DAO extends CrudRepository<E, Integer>> {
	@Autowired
	protected DAO dao;
	
	@Transactional
	public E add(E entity) throws PotagerException {
		return dao.save(entity); 
	}
	
	@Transactional
	public E modify(E entity) {
		return dao.save(entity); 
	}
	
	@Transactional
	public void delete(E entity) {
		dao.delete(entity); 
	}

	@Transactional
	public void deleteById(Integer id) {
		dao.deleteById(id); 
	}
	
	public E findById(Integer id) {
		return dao.findById(id).orElse(null);
	}
	
	public List<E> findAll(){
		return (List<E>) dao.findAll();
	}
	
}
