package br.com.cemim.igor.dao;

import java.util.Collection;

public interface GenericDAO<T> {

    public boolean insert(T obj);
    public int update(T obj);
    public int delete(T obj);
    public Collection<T> listAll();
    public T findByID(int id);

}
