package entities.attributes_links;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="car_attributes_link")
public class Car_attributes_link {

    @Id
    @Column(name="LINK_ID")
    private int LINK_ID;
    @Column(name="CAR_ID")
    private int CAR_ID;
    @Column(name="CAR_ATT_ID")
    private int CAR_ATT_ID;

    public Car_attributes_link(int LINK_ID, int CAR_ID, int CAR_ATT_ID) {
        this.LINK_ID = LINK_ID;
        this.CAR_ID = CAR_ID;
        this.CAR_ATT_ID = CAR_ATT_ID;
    }

    public Car_attributes_link() {

    }

    public int getLINK_ID() {
        return LINK_ID;
    }

    public void setLINK_ID(int LINK_ID) {
        this.LINK_ID = LINK_ID;
    }

    public int getCAR_ID() {
        return CAR_ID;
    }

    public void setCAR_ID(int CAR_ID) {
        this.CAR_ID = CAR_ID;
    }

    public int getCAR_ATT_ID() {
        return CAR_ATT_ID;
    }

    public void setCAR_ATT_ID(int CAR_ATT_ID) {
        this.CAR_ATT_ID = CAR_ATT_ID;
    }
}
