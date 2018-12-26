package com.erkanerturk.petclinic.service;

import java.util.List;

import com.erkanerturk.petclinic.dao.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erkanerturk.petclinic.dao.OwnerRepository;
import com.erkanerturk.petclinic.exception.OwnerNotFoundException;
import com.erkanerturk.petclinic.model.Owner;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PetClinicServiceImpl implements PetClinicService {

    private OwnerRepository ownerRepository;

    private PetRepository petRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Autowired
    public void setPetRepository(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Owner> findOwners(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Owner findOwner(Long id) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(id);
        if (owner == null) throw new OwnerNotFoundException("Owner not found with id :" + id);
        return owner;
    }

    @Override
    public void addOwner(Owner owner) {
        ownerRepository.add(owner);
    }

    @Override
    public void update(Owner owner) {
        ownerRepository.update(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        petRepository.deleteOwnerId(id);
        ownerRepository.delete(id);
    }

}
