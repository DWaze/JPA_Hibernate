package DAO;


import bda2.tp4.jpa.Consumer;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by lhadj on 1/1/2017.
 */
public class ConsumerDAO extends DAO {
    private EntityManager em;
    public ConsumerDAO(EntityManager emf) {
        super(emf);
        this.em =emf;
    }

    @Override
    public boolean create(Object obj) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Consumer mConsumer = (Consumer)obj;
            if(em.contains(obj)){
                em.merge(mConsumer);
            }else{
                em.persist(mConsumer);
            }
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
            Consumer mConsumer = (Consumer)obj;
            em.remove(mConsumer);
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
        Consumer mConsumer = (Consumer)obj;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(mConsumer);
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
    public Consumer find(int id) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Consumer> criteriaQuery = cb.createQuery(Consumer.class);
        Root<Consumer> consumerRoot = criteriaQuery.from(Consumer.class);
        criteriaQuery.select(consumerRoot);
        criteriaQuery.where(cb.equal(consumerRoot.get("id_consumer"),""+id));
        List<Consumer> consumers = em.createQuery(criteriaQuery).getResultList();
        return consumers.get(0);
    }


    public Consumer find(String username,String password) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Consumer> criteriaQuery = cb.createQuery(Consumer.class);
        Root<Consumer> consumerRoot = criteriaQuery.from(Consumer.class);
        criteriaQuery.select(consumerRoot);
        criteriaQuery.where(cb.equal(consumerRoot.get("username"),""+username),cb.equal(consumerRoot.get("password"),""+password));
        List<Consumer> consumers = em.createQuery(criteriaQuery).getResultList();
        if(consumers.size()==0)
            return null;
        else
            return consumers.get(0);
    }

    public List<Consumer> findAll(){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Consumer> cq = cb.createQuery(Consumer.class);
        Root<Consumer> rootEntry = cq.from(Consumer.class);
        CriteriaQuery<Consumer> all = cq.select(rootEntry);
        TypedQuery<Consumer> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
