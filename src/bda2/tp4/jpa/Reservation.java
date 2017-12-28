package bda2.tp4.jpa;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "RESERVATION")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RESERVATION_ID")
    private int id_reservation;
    @Column(name="AMOUNT")
    private double amount;
    @Column(name="CREATION_DATE")
    private Date creationDate;
    @Column(name="STATE")
    private String state;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="CONSUMER_ID")
    private Consumer rConsumer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="PLATE_RESERVATION",
            joinColumns = @JoinColumn(name = "RESERVATION_ID")
            ,inverseJoinColumns = @JoinColumn(name = "PLATE_ID"))
    private Set<Plate> rplate = new HashSet<Plate>();;



    public Reservation(double amount, Date creationDate, String state) {
        this.amount = amount;
        this.creationDate = creationDate;
        this.state = state;
        this.rConsumer = new Consumer();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setrConsumer(Consumer rConsumer) {
        this.rConsumer = rConsumer;
    }

    public void setRplate(Set<Plate> rplate) {
        this.rplate = rplate;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public double getAmount() {
        return amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getState() {
        return state;
    }

    public Consumer getrConsumer() {
        return rConsumer;
    }

    public Set<Plate> getRplate() {
        return rplate;
    }

    public Reservation() {
        rplate = new HashSet<Plate>(); }

    public void addPlate(Plate plate){
        if( !rplate.contains(plate) )
            rplate.add(plate);
    }
    // Association bidirectionnelle one-to-many (bda2.tp4.jpa.Consumer ↔ bda2.tp4.jpa.Reservation)

    public void addConsumer(Consumer consumer) {
        if (!consumer.getArrayReservation().contains(this)) {
            if (rConsumer != null)
                rConsumer.remove(this);
            this.setrConsumer(consumer);
            consumer.getArrayReservation().add(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        Reservation mReservation = (Reservation) obj;
        if(mReservation.getId_reservation()==this.getId_reservation()){
            return true;
        }else{
            return false;
        }
    }
}
