package com.erkanerturk.petclinic.dao;

import java.util.List;

import com.erkanerturk.petclinic.model.Pet;

public interface PetRepository {
	Pet findById(Long id);
	List<Pet> findByOwnerId(Long ownerId);
	void add(Pet pet);
	Pet update(Pet pet);
	void delete(Long id);
	void deleteOwnerId(Long ownerId); 
}
