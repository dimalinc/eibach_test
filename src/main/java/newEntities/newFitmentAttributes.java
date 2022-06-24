package newEntities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="fitment_attributes")
public class newFitmentAttributes {

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
    private List<newFitment> fitmentsList;

    @Override
    public String toString() {
        return "\r\n\\{" +
                "FIT_ATT_ID=" + FIT_ATT_ID +
                ", FIT_ATT_NAME='" + FIT_ATT_NAME + '\'' +
                ", FIT_ATT_VALUE='" + FIT_ATT_VALUE + '\'' +
                /*", fitmentsList=" + fitmentsList +*/
                '}';
    }
}
