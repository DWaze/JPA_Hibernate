package DAO;

import bda2.tp4.jpa.Consumer;
import bda2.tp4.jpa.Evaluation;
import bda2.tp4.jpa.Plate;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EvaluationDAO extends DAO {
    private EntityManager em;
    public EvaluationDAO(EntityManager emf) {
        super(emf);
        this.em =emf;
    }

    @Override
    public boolean create(Object obj) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Evaluation mEvaluation = (Evaluation)obj;
            if(em.contains(obj)){
                em.merge(mEvaluation);
            }else{
                em.persist(mEvaluation);
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
            Evaluation mEvaluation = (Evaluation) obj;
            em.remove(mEvaluation);
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
        Evaluation mEvaluation = (Evaluation)obj;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(mEvaluation);
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
    public Object find(int id) {
        return null;
    }

    public Evaluation find(Consumer mConsumer, Plate mPlate) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Evaluation> criteriaQuery = cb.createQuery(Evaluation.class);
        Root<Evaluation> evaluationRoot = criteriaQuery.from(Evaluation.class);
        criteriaQuery.select(evaluationRoot);
        criteriaQuery.where(cb.equal(evaluationRoot.get("mConsumer"),mConsumer),cb.equal(evaluationRoot.get("mPlate"),mPlate));
        List<Evaluation> evaluation = em.createQuery(criteriaQuery).getResultList();
        if(evaluation.size()==0)
            return null;
        else
            return evaluation.get(0);
    }

    public List<Evaluation> findAll(){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Evaluation> cq = cb.createQuery(Evaluation.class);
        Root<Evaluation> rootEntry = cq.from(Evaluation.class);
        CriteriaQuery<Evaluation> all = cq.select(rootEntry);
        TypedQuery<Evaluation> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
