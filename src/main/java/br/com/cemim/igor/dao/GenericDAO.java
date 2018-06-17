package br.com.cemim.igor.dao;

import java.util.Collection;

public interface GenericDAO<T> {

    public static final int ERRO_OPERACAO = -1;
    public static final int OPERACAO_EXECUTADA = 1;

    public int insert(T obj);
    public int update(T obj);
    public int delete(T obj);
    public Collection<T> listAll();
    public T findByID(int id);

}
