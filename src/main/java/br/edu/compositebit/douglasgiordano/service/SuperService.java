package br.edu.compositebit.douglasgiordano.service;

import java.util.ArrayList;

public abstract class SuperService<T extends Object> {
    public abstract T add(T entity);
    public abstract T get(int id);
    public abstract ArrayList<T> get();
    public abstract void delete();
    public abstract T update();
}
