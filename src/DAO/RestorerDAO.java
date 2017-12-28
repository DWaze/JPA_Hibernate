package DAO;

import bda2.tp4.jpa.Restorer;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RestorerDAO extends DAO {
    private EntityManager em;
    public RestorerDAO(EntityManager emf) {
        super(emf);
        this.em =emf;
    }

    @Override
    public boolean create(Object obj) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Restorer mRestorer = (Restorer)obj;
            em.persist(mRestorer);
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
            Restorer mRestorer = (Restorer)obj;
            em.remove(mRestorer);
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
            Restorer mRestorer = (Restorer)obj;
            em.merge(mRestorer);
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
    public Restorer find(int id) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Restorer> criteriaQuery = cb.createQuery(Restorer.class);
        Root<Restorer> restorerRoot = criteriaQuery.from(Restorer.class);
        criteriaQuery.select(restorerRoot);
        criteriaQuery.where(cb.equal(restorerRoot.get("id_restorer"),""+id));
        List<Restorer> reservation = em.createQuery(criteriaQuery).getResultList();
        if(reservation.size()==0)
            return null;
        else
            return reservation.get(0);
    }

    public List<Restorer> findAll(){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Restorer> cq = cb.createQuery(Restorer.class);
        Root<Restorer> rootEntry = cq.from(Restorer.class);
        CriteriaQuery<Restorer> all = cq.select(rootEntry);
        TypedQuery<Restorer> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
