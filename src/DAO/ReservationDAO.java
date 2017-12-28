package DAO;

import bda2.tp4.jpa.Reservation;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ReservationDAO extends DAO {
    private EntityManager em;
    public ReservationDAO(EntityManager emf) {
        super(emf);
        this.em =emf;
    }

    @Override
    public boolean create(Object obj) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Reservation mReservation = (Reservation)obj;
            if(em.contains(obj)){
                em.merge(mReservation);
            }else{
                em.persist(mReservation);
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
            Reservation mReservation = (Reservation)obj;
            em.remove(mReservation);
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
        Reservation mReservation = (Reservation)obj;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(mReservation);
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
    public Reservation find(int id) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = cb.createQuery(Reservation.class);
        Root<Reservation> reservationRoot = criteriaQuery.from(Reservation.class);
        criteriaQuery.select(reservationRoot);
        criteriaQuery.where(cb.equal(reservationRoot.get("id_reservation"),""+id));
        List<Reservation> reservation = em.createQuery(criteriaQuery).getResultList();
        if(reservation.size()==0)
            return null;
        else
            return reservation.get(0);
    }

    public List<Reservation> findAll(){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
        Root<Reservation> rootEntry = cq.from(Reservation.class);
        CriteriaQuery<Reservation> all = cq.select(rootEntry);
        TypedQuery<Reservation> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
