package com.erkanerturk.petclinic.service;

import java.util.List;

import com.erkanerturk.petclinic.exception.OwnerNotFoundException;
import com.erkanerturk.petclinic.model.Owner;

public interface PetClinicService {
	List<Owner> findOwners();
	List<Owner> findOwners(String lastName);
	Owner findOwner(Long id) throws OwnerNotFoundException;
	void addOwner(Owner owner);
	void update(Owner owner);
	void deleteOwner(Long id);
}
