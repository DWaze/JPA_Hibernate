package bda2.tp4.jpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EVALUATION")
public class Evaluation implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="CONSUMER_ID")
    private Consumer mConsumer;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PLATE_ID")
    private Plate mPlate ;

    @Column(name="PLATE_RATE")
    private int plateRate;

    public Evaluation(Consumer mConsumer, Plate mPlate, int plateRate) {
        this.mConsumer = mConsumer;
        this.mPlate = mPlate;
        this.plateRate = plateRate;
    }

    public Evaluation() {
    }

    public void setPlateRate(int plateRate) {
        this.plateRate = plateRate;
    }

    public Consumer getmConsumer() {
        return mConsumer;
    }


    public Plate getmPlate() {
        return mPlate;
    }

    public void setmConsumer(Consumer mConsumer) {
        this.mConsumer = mConsumer;
    }

    public void setmPlate(Plate mPlate) {
        this.mPlate = mPlate;
    }



    public void addConsumer(Consumer consumer) {
        if (!consumer.getArrayEvaluationConsumer().contains(this)) {
            if (this.getmConsumer() != null)
                this.getmConsumer().remove(this);
            this.setmConsumer(consumer);
            consumer.getArrayEvaluationConsumer().add(this);
        }
    }



    @Override
    public boolean equals(Object obj) {
        Evaluation mEvaluation = (Evaluation)obj;
        return (mEvaluation.mConsumer.getId_consumer()==this.getmConsumer().getId_consumer() &&
                mEvaluation.mPlate.getId_plate()==this.getmPlate().getId_plate());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getPlateRate() {
        return plateRate;
    }


}
