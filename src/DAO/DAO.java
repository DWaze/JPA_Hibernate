package DAO;

import javax.persistence.EntityManager;

/**
 * Created by lhadj on 1/1/2017.
 */
public abstract class DAO<T> {
    protected EntityManager emf = null ;

    public DAO(EntityManager emf){
        this.emf = emf;
    }

    public abstract boolean create(T obj);
    public abstract boolean delete(T obj);
    public abstract boolean update(T obj);
    public abstract T find(int id);
}
