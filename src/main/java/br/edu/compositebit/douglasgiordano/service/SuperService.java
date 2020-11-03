package br.edu.compositebit.douglasgiordano.service;

import br.edu.compositebit.douglasgiordano.model.entities.exception.BadEntityException;
import br.edu.compositebit.douglasgiordano.model.entities.exception.EntityNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author Douglas Montanha Giordano
 * Super class responsible for encapsulation and definition of attributes and methods in common among service.
 */
public abstract class SuperService<T extends Object> {
    /**
     * Add new entity.
     * @param entity
     * @return entity with ID
     * @throws BadEntityException
     */
    public abstract T add(T entity) throws BadEntityException;

    /**
     * Get entity by id
     * @param id
     * @return entity
     * @throws EntityNotFoundException
     */
    public abstract T get(int id) throws EntityNotFoundException;

    /**
     * Get entities list
     * @return list
     */
    public abstract ArrayList<T> get();

    /**
     * Delete entity by id
     * @param id
     */
    public abstract void delete(Integer id);

    /**
     * Update entity
     * @param id
     * @param entity
     * @return entities
     * @throws EntityNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws BadEntityException
     */
    public abstract T update(Integer id, T entity) throws EntityNotFoundException, InvocationTargetException, IllegalAccessException, BadEntityException;
}
