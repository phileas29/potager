package com.example.Potager.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class CRUDManagerWS<E,DAO extends CrudRepository<E, Integer>> {
	@Autowired
	protected DAO dao;
	
	/**
	 * @return
	 */
	@GetMapping("")
	public List<E> show() {
		return (List<E>) dao.findAll();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public E show(@PathVariable("id") Integer id) {
		return dao.findById(id).orElse(null);
	}
	
	/**
	 * @param entity
	 */
	@PostMapping("")
	public void add(@RequestBody E entity) {
		dao.save(entity);
	}
	
	/**
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		dao.deleteById(id);
	}
	
	/**
	 * @param entity
	 */
	@PutMapping("")
	public void update(@RequestBody E entity) {
		dao.save(entity);
	}
}
