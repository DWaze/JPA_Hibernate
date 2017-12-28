package DAO;

import bda2.tp4.jpa.Restaurant;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RestaurantDAO extends DAO {
    private EntityManager em;
    public RestaurantDAO(EntityManager emf) {
        super(emf);
        this.em =emf;
    }

    @Override
    public boolean create(Object obj) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Restaurant mRestaurant = (Restaurant)obj;
            em.persist(mRestaurant);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean delete(Object obj) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Restaurant mRestaurant = (Restaurant)obj;
            em.remove(mRestaurant);
            tx.commit();
            return true;
        }catch (HibernateException e){
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean update(Object obj) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Restaurant mRestaurant = (Restaurant)obj;
            em.merge(mRestaurant);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    @Override
    public Restaurant find(int id) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Restaurant> criteriaQuery = cb.createQuery(Restaurant.class);
        Root<Restaurant> restaurantRoot = criteriaQuery.from(Restaurant.class);
        criteriaQuery.select(restaurantRoot);
        criteriaQuery.where(cb.equal(restaurantRoot.get("id_restaurant"),""+id));
        List<Restaurant> reservation = em.createQuery(criteriaQuery).getResultList();
        if(reservation.size()==0)
            return null;
        else
            return reservation.get(0);
    }

    public List<Restaurant> findAll(){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Restaurant> cq = cb.createQuery(Restaurant.class);
        Root<Restaurant> rootEntry = cq.from(Restaurant.class);
        CriteriaQuery<Restaurant> all = cq.select(rootEntry);
        TypedQuery<Restaurant> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
