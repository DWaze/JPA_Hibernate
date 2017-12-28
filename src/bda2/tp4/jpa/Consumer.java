package bda2.tp4.jpa;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "CONSUMER")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CONSUMER_ID")
    protected int id_consumer;
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;
    @Column(name="EMAIL")
    private String email;
    @Column(name="LATITUDE")
    private double latitude;
    @Column(name="LONGITUDE")
    private double longitude;
    @Column(name="CON_DATE")
    private Date con_date;
    @Column(name="USERNAME")
    protected String username;
    @Column(name="PASSWORD")
    protected String password;
    @Column(name="FIRST_NAME")
    protected String firstname;
    @Column(name="LAST_NAME")
    protected String lastname;

    @OneToMany(mappedBy = "mConsumer", cascade = CascadeType.ALL)
    private Set<Evaluation> rEvaluation;

    @OneToMany(mappedBy = "rConsumer", cascade = CascadeType.ALL)
    private Set<Reservation> rReservation;


    public Consumer() {
    }

    public Consumer(String phoneNumber, String email, double latitude, double longitude, Date con_date, String username, String password, String firstname, String lastname) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.con_date = con_date;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void setId_consumer(int id_consumer) {
        this.id_consumer = id_consumer;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setrEvaluation(Set<Evaluation> rEvaluation) {
        this.rEvaluation = rEvaluation;
    }

    public void setrReservation(Set<Reservation> rReservation) {
        this.rReservation = rReservation;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCon_date(Date con_date) {
        this.con_date = con_date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public int getId_consumer() {
        return id_consumer;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }



    public Set<Evaluation> getrEvaluation() {
        return rEvaluation;
    }

    public Set<Reservation> getrReservation() {
        return rReservation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Date getCon_date() {
        return con_date;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Set<Evaluation> getArrayEvaluationConsumer() {
        return (rEvaluation);
    }

    public Set<Reservation> getArrayReservation() {
        return (rReservation);
    }


    // Association unidirectionnelle one-to-many (bda2.tp4.jpa.Consumer → evaluation) ↔

    public void remove(Evaluation evaluation) {
        rEvaluation.remove(evaluation);
    }

    public void addEvaluation(Evaluation evaluation) {
        if (!rEvaluation.contains(evaluation)) {
            if (evaluation.getmConsumer() != null)
                evaluation.getmConsumer().remove(evaluation);
            evaluation.setmConsumer(this);
            rEvaluation.add(evaluation);
        }
    }

    // Association bidirectionnelle one-to-many (bda2.tp4.jpa.Consumer ↔ bda2.tp4.jpa.Reservation)
    public void remove(Reservation reservation) {
        rReservation.remove(reservation);
    }

    public void addReservation(Reservation reservation) {
        if (!rReservation.contains(reservation)) {
            if (reservation.getrConsumer() != null)
                reservation.getrConsumer().remove(reservation);
            reservation.setrConsumer(this);
            rReservation.add(reservation);
        }
    }

    @Override
    public boolean equals(Object obj) {
        Consumer mConsumer = (Consumer)obj;
        if(mConsumer.getId_consumer()==this.getId_consumer()){
            return true;
        }else{
            return false;
        }
    }

    public void signUp() {
    }

    public void signIn() {
    }

    public void addReservation() {
    }

}
