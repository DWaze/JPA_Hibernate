package bda2.tp4.jpa;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "PLATE")
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PLATE_ID")
    private int id_plate;
    @Column(name="PLATE_NAME")
    private String plate_name;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="PRICE")
    private int price;
    @Column(name="PHOTO_PATH")
    private String photoPath;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "PLATE_ID")
    private Set<Offer> rOffer=new HashSet<Offer>();

    public Plate(String plate_name, String description, int price, String photoPath) {
        this.plate_name = plate_name;
        this.description = description;
        this.price = price;
        this.photoPath = photoPath;
    }

    public Plate() {

    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setrOffer(Set<Offer> rOffer) {
        this.rOffer = rOffer;
    }

    public int getId_plate() {

        return id_plate;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getPhotoPath() {
        return photoPath;
    }


    public Set<Offer> getrOffer() {
        return rOffer;
    }


    // composition one-to-many (bda2.tp4.jpa.Plate <>→ bda2.tp4.jpa.Offer)
    public void addOffer(Offer offer) {
        if (!rOffer.contains(offer))
            rOffer.add(offer);
    }

    public void removeOffer(Offer offer){
        rOffer.remove(offer);
    }

    public void removeAllOffers(){
        //for(int i=0;i<rOffer.size();i++){}
        rOffer.clear();
    }

    @Override
    public boolean equals(Object obj) {
        Plate mPlate = (Plate) obj;
        if(mPlate.getId_plate()==this.getId_plate()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
