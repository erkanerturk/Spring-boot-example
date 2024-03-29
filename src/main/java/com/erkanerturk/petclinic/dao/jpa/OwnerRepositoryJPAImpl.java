package com.erkanerturk.petclinic.dao.jpa;

import com.erkanerturk.petclinic.dao.OwnerRepository;
import com.erkanerturk.petclinic.model.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("ownerRepository")
public class OwnerRepositoryJPAImpl implements OwnerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Owner> findAll() {
        return entityManager.createQuery("from Owner", Owner.class).getResultList();
    }

    @Override
    public Owner findById(Long id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return entityManager.createQuery("from Owner where lastName = :lastName", Owner.class)
                .setParameter("lastName", lastName).getResultList();
    }

    @Override
    public void add(Owner owner) {
        entityManager.persist(owner);
    }

    @Override
    public Owner update(Owner owner) {
        return entityManager.merge(owner);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Owner.class, id));
    }
}
