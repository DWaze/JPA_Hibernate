package bda2.tp4.jpa;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RESTAURANT_ID")
    private int id_restaurant;
    @Column(name="RESTAURANT_NAME")
    private String name_restaurant;
    @Column(name="PHOTO_PATH")
    private String photoPath;
    @Column(name="R_STREET")
    private String r_street;
    @Column(name="ZIP_CODE")
    private String zipCode;
    @Column(name="CITY")
    private String city;
    @Column(name="COUNTRY")
    private String country;
    @Column(name="LATITUDE")
    private double latitude;
    @Column(name="LONGITUDE")
    private double longitude;
    @Column(name="RES_DATE")
    private Date res_date;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "RESTAURANT_ID")
    private Set<Plate> rPlate;

    public Restaurant(String name_restaurant, String photoPath, String r_street, String zipCode, String city, String country, double latitude, double longitude, Date res_date) {
        this.name_restaurant = name_restaurant;
        this.photoPath = photoPath;
        this.r_street = r_street;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.res_date = res_date;
        this.rPlate = new HashSet<Plate>();
    }

    public Restaurant() {
    }


    public void setR_street(String r_street) {
        this.r_street = r_street;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setRes_date(Date res_date) {
        this.res_date = res_date;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public void setName_restaurant(String name_restaurant) {
        this. name_restaurant = name_restaurant;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setrPlate(Set<Plate> rPlate) {
        this.rPlate = rPlate;
    }



    public int getId_restaurant() {
        return id_restaurant;
    }

    public String getR_street() {

        return r_street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Date getRes_date() {
        return res_date;
    }

    public String getName_restaurant() {
        return  name_restaurant;
    }

    public String getPhotoPath() {
        return photoPath;
    }


    public Set<Plate> getrPlate() {
        return rPlate;
    }

    // composition one-to-one (bda2.tp4.jpa.Restaurant <>→ PositionM)

    public void addPlate(Plate plate) {
        if (!rPlate.contains(plate))
            rPlate.add(plate);
    }

    public void removePlate(Plate plate) {
        if (!rPlate.contains(plate)){
            //1-remove offer
            plate.removeAllOffers();
            //2-remove plate
            rPlate.remove(plate);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void removeAllPlates(){
        for (Plate plate : rPlate) {
            removePlate(plate);
        }
    }

    @Override
    public boolean equals(Object obj) {
        Restaurant mRestaurant = (Restaurant) obj;
        if(mRestaurant.getId_restaurant()==this.getId_restaurant()){
            return true;
        }else{
            return false;
        }
    }

}
