package com.erkanerturk.petclinic.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erkanerturk.petclinic.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Long>{

}