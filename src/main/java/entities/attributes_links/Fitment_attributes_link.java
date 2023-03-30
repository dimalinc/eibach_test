package entities.attributes_links;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fitment_attributes_link")
public class Fitment_attributes_link {

    @Id
    @Column(name="LINK_ID")
    private int LINK_ID;
    @Column(name="FIT_ID")
    private int FIT_ID;
    @Column(name="FIT_ATT_ID")
    private int FIT_ATT_ID;

    public Fitment_attributes_link(int LINK_ID, int FIT_ID, int FIT_ATT_ID) {
        this.LINK_ID = LINK_ID;
        this.FIT_ID = FIT_ID;
        this.FIT_ATT_ID = FIT_ATT_ID;
    }

    public Fitment_attributes_link() {

    }

    public int getLINK_ID() {
        return LINK_ID;
    }

    public void setLINK_ID(int LINK_ID) {
        this.LINK_ID = LINK_ID;
    }

    public int getFIT_ID() {
        return FIT_ID;
    }

    public void setFIT_ID(int FIT_ID) {
        this.FIT_ID = FIT_ID;
    }

    public int getFIT_ATT_ID() {
        return FIT_ATT_ID;
    }

    public void setFIT_ATT_ID(int FIT_ATT_ID) {
        this.FIT_ATT_ID = FIT_ATT_ID;
    }
}
