package com.erkanerturk.petclinic.dao.jpa;

import com.erkanerturk.petclinic.dao.PetRepository;
import com.erkanerturk.petclinic.model.Pet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("petRepository")
public class PetRepositoryJPAImpl implements PetRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Pet findById(Long id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    public List<Pet> findByOwnerId(Long ownerId) {
        return entityManager.createQuery("from Pet where owner.id = :ownerId", Pet.class)
                .setParameter("ownerId", ownerId).getResultList();
    }

    @Override
    public void add(Pet pet) {
        entityManager.persist(pet);
    }

    @Override
    public Pet update(Pet pet) {
        return entityManager.merge(pet);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Pet.class, id));
    }

    @Override
    public void deleteOwnerId(Long ownerId) {
        entityManager.createQuery("delete from Pet where owner.id = :ownerId")
                .setParameter("ownerId", ownerId).executeUpdate();
    }
}
