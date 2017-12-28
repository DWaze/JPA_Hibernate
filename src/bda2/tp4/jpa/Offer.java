package bda2.tp4.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Offer")
public class Offer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="OFFER_ID")
    private int id_offer;
    @Column(name="DISCOUNT")
    private double discount;
    @Column(name="BEGIN_DATE")
    @Temporal(TemporalType.DATE)
    private Date beginDate;
    @Column(name="END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public Offer(double discount, Date beginDate, Date endDate) {
        this.discount = discount;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Offer() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void setId_offer(int id_offer) {
        this.id_offer = id_offer;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getId_offer() {

        return id_offer;
    }

    public double getDiscount() {
        return discount;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object obj) {
        Offer mOffer = (Offer) obj;
        if(mOffer.getId_offer()==this.getId_offer()){
            return true;
        }else{
            return false;
        }
    }
}
