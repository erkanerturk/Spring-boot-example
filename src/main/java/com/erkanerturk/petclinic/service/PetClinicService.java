package com.erkanerturk.petclinic.service;

import java.util.List;

import com.erkanerturk.petclinic.exception.OwnerNotFoundException;
import com.erkanerturk.petclinic.exception.VetNotFoundException;
import com.erkanerturk.petclinic.model.Owner;
import com.erkanerturk.petclinic.model.Vet;

public interface PetClinicService {
	List<Owner> findOwners();
	List<Owner> findOwners(String lastName);
	Owner findOwner(Long id) throws OwnerNotFoundException;
	void addOwner(Owner owner);
	void update(Owner owner);
	void deleteOwner(Long id);

	List<Vet> findVets();
	Vet findVet(Long id) throws VetNotFoundException;
}
