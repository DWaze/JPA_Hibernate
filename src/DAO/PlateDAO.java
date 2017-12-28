package DAO;

import bda2.tp4.jpa.Plate;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PlateDAO extends DAO {
    private EntityManager em;
    public PlateDAO(EntityManager emf) {
        super(emf);
        this.em =emf;
    }

    @Override
    public boolean create(Object obj) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Plate mPlate = (Plate)obj;
            if(em.contains(obj)){
                em.merge(mPlate);
            }else{
                em.persist(mPlate);
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
            Plate mPlate = (Plate)obj;
            em.refresh(mPlate);
            em.remove(mPlate);
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
            Plate mPlate = (Plate)obj;
            em.merge(mPlate);
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
    public Plate find(int id) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Plate> criteriaQuery = cb.createQuery(Plate.class);
        Root<Plate> plateRoot = criteriaQuery.from(Plate.class);
        criteriaQuery.select(plateRoot);
        criteriaQuery.where(cb.equal(plateRoot.get("id_plate"),""+id));
        List<Plate> plate = em.createQuery(criteriaQuery).getResultList();
        if(plate.size()==0)
            return null;
        else
            return plate.get(0);
    }

    public List<Plate> findAll(){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Plate> cq = cb.createQuery(Plate.class);
        Root<Plate> rootEntry = cq.from(Plate.class);
        CriteriaQuery<Plate> all = cq.select(rootEntry);
        TypedQuery<Plate> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
