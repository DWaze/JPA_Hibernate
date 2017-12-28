package bda2.tp4.jpa;


import javax.persistence.*;

@Entity
@Table(name = "RESTORER")
public class Restorer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RESTORER_ID")
    protected int id_restorer;
    @Column(name="REGISTER_NUMBER")
    private String registerNumber;
    @Column(name="USERNAME")
    protected String username;
    @Column(name="PASSWORD")
    protected String password;
    @Column(name="FIRSTNAME")
    protected String firstname;
    @Column(name="LASTNAME")
    protected String lastname;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(unique = true, name= "RESTAURANT_ID")
    private Restaurant rRestaurant;

    public Restorer() {
    }

    public void setrRestaurant(Restaurant rRestaurant) {
        this.rRestaurant = rRestaurant;
    }

    public Restaurant getrRestaurant() {

        return rRestaurant;
    }

    public Restorer(String registerNumber, String username, String password, String firstname, String lastname) {
        this.registerNumber = registerNumber;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.rRestaurant = new Restaurant();
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getRegisterNumber() {

        return registerNumber;
    }

    public void setId_restorer(int id_restorer) {
        this.id_restorer = id_restorer;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getId_restorer() {

        return id_restorer;
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

    //_________________________________________
    //Association unidirectionnelle one-to-one

    public void addRestaurant(Restaurant restaurant) {
        this.setRestaurant(restaurant);
    }

    public Restaurant getRestaurant() {
        return rRestaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.rRestaurant = restaurant;
    }

    public void removeRestaurant(Restaurant restaurant) {
        //1-remove all plates
        this.rRestaurant.removeAllPlates();
        //3- remove the restaurant
        this.rRestaurant = null;
    }
    //_________________________________________
    public void addPlate(){}

    @Override
    public boolean equals(Object obj) {
        Restorer mRestorer = (Restorer) obj;
        if(mRestorer.getId_restorer()==this.getId_restorer()){
            return true;
        }else{
            return false;
        }
    }
}
