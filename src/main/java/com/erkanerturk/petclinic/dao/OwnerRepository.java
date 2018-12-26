package com.erkanerturk.petclinic.dao;

import java.util.List;

import com.erkanerturk.petclinic.model.Owner;

public interface OwnerRepository {
	List<Owner> findAll();
	Owner findById(Long id);
	List<Owner> findByLastName(String lastName);
	void add(Owner owner);
	Owner update(Owner owner);
	void delete(Long id);
}
