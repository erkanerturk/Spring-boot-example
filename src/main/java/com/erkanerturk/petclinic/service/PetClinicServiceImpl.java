package com.erkanerturk.petclinic.service;

import java.util.List;

import com.erkanerturk.petclinic.dao.PetRepository;
import com.erkanerturk.petclinic.dao.jpa.VetRepository;
import com.erkanerturk.petclinic.exception.VetNotFoundException;
import com.erkanerturk.petclinic.model.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.erkanerturk.petclinic.dao.OwnerRepository;
import com.erkanerturk.petclinic.exception.OwnerNotFoundException;
import com.erkanerturk.petclinic.model.Owner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PetClinicServiceImpl implements PetClinicService {

    private OwnerRepository ownerRepository;

    private PetRepository petRepository;

    @Autowired
    private JavaMailSender mailSender;

    private VetRepository vetRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Autowired
    public void setPetRepository(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Autowired
    public void setVetRepository(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Secured(value = {"ROLE_USER", "ROLE_EDITOR"})
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Owner> findOwners(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Owner findOwner(Long id) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(id);
        if (owner == null) throw new OwnerNotFoundException("Owner not found with id :" + id);
        return owner;
    }

    @Override
    public void addOwner(Owner owner) {
        ownerRepository.add(owner);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("k@s");
        msg.setTo("m@y");
        msg.setSubject("Owner created!");
        msg.setText("Owner entity with id :" + owner.getId() + " created successfully");

        mailSender.send(msg);
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

    @Override
    public List<Vet> findVets() {
        return vetRepository.findAll();
    }

    @Override
    public Vet findVet(Long id) throws VetNotFoundException {
        return vetRepository.findById(id).orElseThrow(() -> new VetNotFoundException("Vet not found by id :" + id));
    }
}
