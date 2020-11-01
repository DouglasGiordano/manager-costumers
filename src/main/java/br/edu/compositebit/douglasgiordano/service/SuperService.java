package br.edu.compositebit.douglasgiordano.service;

import br.edu.compositebit.douglasgiordano.model.entities.exception.EntityNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class SuperService<T extends Object> {
    public abstract T add(T entity);
    public abstract T get(int id) throws EntityNotFoundException;
    public abstract ArrayList<T> get();
    public abstract void delete(Integer id);
    public abstract T update(Integer id, T entity) throws EntityNotFoundException, InvocationTargetException, IllegalAccessException;
}
