package com.neuralnoise.map.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.google.common.base.Preconditions;

@Repository
public abstract class AbstractDAO<T extends Serializable> {
 
    private Class<T> clazz;
    
    @PersistenceContext
    private EntityManager entityManager;
 
    public AbstractDAO(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    } 
 
    public T getById(final Long id) {
        Preconditions.checkArgument(id != null);
        return getEntityManager().find(clazz, id);
    }
 
    public List<T> getAll() {
        return getEntityManager().createQuery("from " + clazz.getName()).getResultList();
    }
 
    public void create(final T entity) {
        Preconditions.checkNotNull(entity);
        getEntityManager().persist(entity);
    }
 
    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getEntityManager().merge(entity);
    }
 
    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getEntityManager().remove(entity);
    }
 
    public void deleteById(final Long entityId) {
        final T entity = getById(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }
 
    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
 
    /**
     * @param entityManager the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}