package DAO;

import bda2.tp4.jpa.Offer;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OfferDAO extends DAO {
    private EntityManager em;
    public OfferDAO(EntityManager emf) {
        super(emf);
        this.em =emf;
    }

    @Override
    public boolean create(Object obj) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Offer mOffer = (Offer)obj;
            em.persist(mOffer);
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
            Offer mOffer = (Offer) obj;
            em.remove(mOffer);
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
            Offer mOffer = (Offer)obj;
            em.merge(mOffer);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public Offer find(int id) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = cb.createQuery(Offer.class);
        Root<Offer> offerRoot = criteriaQuery.from(Offer.class);
        criteriaQuery.select(offerRoot);
        criteriaQuery.where(cb.equal(offerRoot.get("id_offer"),""+id));
        List<Offer> reservation = em.createQuery(criteriaQuery).getResultList();
        if(reservation.size()==0)
            return null;
        else
            return reservation.get(0);
    }

    public List<Offer> findAll(){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Offer> cq = cb.createQuery(Offer.class);
        Root<Offer> rootEntry = cq.from(Offer.class);
        CriteriaQuery<Offer> all = cq.select(rootEntry);
        TypedQuery<Offer> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
