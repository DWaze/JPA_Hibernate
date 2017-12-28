import DAO.*;
import bda2.tp4.jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class test {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miam-unit",new Properties());

        EntityManager em = emf.createEntityManager();

//        HashSet<Evaluation> mEvaluation = new HashSet<Evaluation>();


        ////               Consumer

        Consumer mConsumer = new Consumer("0665529928","mail@mail.com",23.45265,36.36526,new Date(2017-1900,11,20),"Redha","0215PA","Redha","Abbassen");
        Consumer mConsumer2 = new Consumer("0665281981","mail2@mail.com",22.36526,30.251981,new Date(2017-1900,7,20),"boulhendi","a5z1d6az","aymen","boulhendi");

        Plate mPlate = new Plate("Trida","Trida",2300,"0328/4684/4684/6zerzer/");
        Plate mPlate2 = new Plate("Omelete","golden Omelte",1100,"0328/468zez4/4684/6zerzer/");
        Set<Plate> mListPlate = new HashSet<Plate>();
        mListPlate.add(mPlate);
        mListPlate.add(mPlate2);

        Reservation mReservation = new Reservation(23.25,new Date(2017-1900,2,2),"active");
        mReservation.setrConsumer(mConsumer);
        mReservation.setRplate(mListPlate);
        HashSet<Reservation> mListReservation = new HashSet<Reservation>();
        mListReservation.add(mReservation);

        mConsumer.setrReservation(mListReservation);

        ConsumerDAO mConsumerDAO = new ConsumerDAO(em);

        mConsumerDAO.create(mConsumer);
//        mConsumerDAO.delete(mConsumer);
        mConsumerDAO.create(mConsumer2);
        mConsumer.setEmail("maeaefzafe@mail.com");
        mConsumerDAO.update(mConsumer);
        Consumer mGetConsumer = mConsumerDAO.find("boulhendia","a5z1d6az");
        List<Consumer> mGetAllConsumer = mConsumerDAO.findAll();





        ////               Reservation

        Reservation mReservation2 = new Reservation(30,new Date(2017-1900,3,6),"deleted");

        ReservationDAO reservationDAO = new ReservationDAO(em);

        reservationDAO.create(mReservation2);

        mReservation2.setrConsumer(mConsumer2);
        Set<Plate> mListPlate2 = new HashSet<Plate>();
        mListPlate2.add(mPlate2);
        mReservation2.setRplate(mListPlate2);
        mListPlate2.add(mPlate);
        mReservation2.setRplate(mListPlate2);

        reservationDAO.update(mReservation2);

        Reservation mGetReservation = reservationDAO.find(mReservation2.getId_reservation());
        List<Reservation> mGetAllReservation = reservationDAO.findAll();

//        reservationDAO.delete(mReservation2);






        ////               Evaluation

        Evaluation mEvaluation = new Evaluation(mConsumer,mPlate,4);
        EvaluationDAO evaluationDAO = new EvaluationDAO(em);
        evaluationDAO.create(mEvaluation);
        mEvaluation.setPlateRate(10);
        evaluationDAO.update(mEvaluation);
        Evaluation mGetEvaluation=evaluationDAO.find(mConsumer,mPlate);
        List<Evaluation> mGetAllEvaluation=evaluationDAO.findAll();
//        evaluationDAO.delete(mEvaluation);



//
//
//
        ////               Plate

        Plate mPlate3 = new Plate("Trida","golden super trida",2200,"032818/46882zez4/4684/6zerzer/");
        PlateDAO mPlateDAO = new PlateDAO(em);
        mPlateDAO.create(mPlate3);
        mPlate3.setDescription("chekhchokha super golden royal");
        mPlateDAO.update(mPlate3);
        mPlateDAO.delete(mPlate3);
        evaluationDAO.delete(mEvaluation);
        reservationDAO.delete(mReservation);
        reservationDAO.delete(mReservation2);
        Plate mGetPlate = mPlateDAO.find(mPlate.getId_plate());
        List<Plate> mGetAllPlate = mPlateDAO.findAll();
//
//
//
//
//
//
//
//
//
        ////               Restorer

        Restorer mRestorer = new Restorer("651651","hamid536","65z16z1efze","hamid","bigou");
        RestorerDAO mRestorerDAO = new RestorerDAO(em);
        mRestorerDAO.create(mRestorer);
        mRestorer.setRegisterNumber("65198165198");
        mRestorerDAO.update(mRestorer);
        Restorer mGetRestorer = mRestorerDAO.find(mRestorer.getId_restorer());
        List<Restorer> mGetAllRestorer = mRestorerDAO.findAll();
        mRestorerDAO.delete(mRestorer);











//        ////               Restaurant
//
        Restaurant mRestaurant = new Restaurant("el bahdja","/686/zef6/zfe8/fzfz","khroub cite les 1000","25100","constantine","algeria",26.256,32.25651,new Date(2017-1900,2,2));
        RestaurantDAO mRestaurantDAO = new RestaurantDAO(em);
        mRestaurantDAO.create(mRestaurant);
        mRestaurant.addPlate(mPlate);
        mRestaurant.addPlate(mPlate2);
        mRestaurant.addPlate(mPlate3);
        mRestaurantDAO.update(mRestaurant);
        Restaurant mGetRestaurant = mRestaurantDAO.find(mRestaurant.getId_restaurant());
        List<Restaurant> mGetAllRestaurant = mRestaurantDAO.findAll();
        mRestaurantDAO.delete(mRestaurant);
//
//
//
//
//
        ////               Offer

        Offer mOffer = new Offer(20,new Date(2017-1900,02,02),new Date(2017,02,02));
        OfferDAO mOfferDAO = new OfferDAO(em);
        mOfferDAO.create(mOffer);
        mOffer.setDiscount(10);
        mOffer.setBeginDate(new Date(2016-1900,02,02));
        mOfferDAO.update(mOffer);
        Offer mGetOffer = mOfferDAO.find(mOffer.getId_offer());
        List<Offer> mGetAllOffer = mOfferDAO.findAll();
        mPlate3.addOffer(mOffer);
        mPlateDAO.update(mPlate3);
        mPlate3.getrOffer().remove(mOffer);
        mOfferDAO.delete(mOffer);
//
//
//
    em.close();
    emf.close();

    }
}