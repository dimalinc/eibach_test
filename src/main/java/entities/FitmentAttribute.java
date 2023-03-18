package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="fitment_attributes")
public class FitmentAttribute {

    @Id
    @Column(name="FIT_ATT_ID")
    private int FIT_ATT_ID;
    @Column(name="FIT_ATT_NAME")
    private String FIT_ATT_NAME;
    @Column(name="FIT_ATT_VALUE")
    private String FIT_ATT_VALUE;

    @ManyToMany
    @JoinTable(name="fitment_attributes_link",
            joinColumns = @JoinColumn(name="FIT_ATT_ID"),
            inverseJoinColumns = @JoinColumn(name="FIT_ID"))
    private List<Fitment> fitmentsList;

    @Override
    public String toString() {
        return "\r\n\\{" +
                "FIT_ATT_ID=" + FIT_ATT_ID +
                ", FIT_ATT_NAME='" + FIT_ATT_NAME + '\'' +
                ", FIT_ATT_VALUE='" + FIT_ATT_VALUE + '\'' +
                /*", fitmentsList=" + fitmentsList +*/
                '}';
    }

    public int getFIT_ATT_ID() {
        return FIT_ATT_ID;
    }

    public void setFIT_ATT_ID(int FIT_ATT_ID) {
        this.FIT_ATT_ID = FIT_ATT_ID;
    }

    public String getFIT_ATT_NAME() {
        return FIT_ATT_NAME;
    }

    public void setFIT_ATT_NAME(String FIT_ATT_NAME) {
        this.FIT_ATT_NAME = FIT_ATT_NAME;
    }

    public String getFIT_ATT_VALUE() {
        return FIT_ATT_VALUE;
    }

    public void setFIT_ATT_VALUE(String FIT_ATT_VALUE) {
        this.FIT_ATT_VALUE = FIT_ATT_VALUE;
    }

    public List<Fitment> getFitmentsList() {
        return fitmentsList;
    }

    public void setFitmentsList(List<Fitment> fitmentsList) {
        this.fitmentsList = fitmentsList;
    }
}
