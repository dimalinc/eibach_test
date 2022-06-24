package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="fitments")
public class Fitment {

    @Id
    @Column(name="FIT_ID")
    private int FIT_ID;
    /*@Column(name = "CAR_ID")
    private int CAR_ID;*/

    /*@Column(name="ITEM_ID")
    private int ITEM_ID;*/

    @ManyToMany
    @JoinTable(name="fitment_attributes_link",
            joinColumns = @JoinColumn(name="FIT_ID"),
            inverseJoinColumns = @JoinColumn(name="FIT_ATT_ID"))
    private List<FitmentAttributes> fitmentAttributesList;

    @OneToOne
    @JoinColumn(name="CAR_ID")
    private Car fitmentCar;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item fitmentItem;


  //private List<Item> fitmentItemsList ;


   /*
    @ManyToMany
    @JoinTable(name="items",
            joinColumns = @JoinColumn(name="ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name="CAR_ATT_ID"))
    private List<Item> fitmentItemsList;*/

    public int getFIT_ID() {
        return FIT_ID;
    }

    public void setFIT_ID(int FIT_ID) {
        this.FIT_ID = FIT_ID;
    }

    /*public int getCAR_ID() {
        return CAR_ID;
    }

    public void setCAR_ID(int CAR_ID) {
        this.CAR_ID = CAR_ID;
    }*/

 /*   public int getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(int ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }*/

    public List<FitmentAttributes> getFitmentAttributesList() {
        return fitmentAttributesList;
    }

    public void setFitmentAttributesList(List<FitmentAttributes> fitmentAttributesList) {
        this.fitmentAttributesList = fitmentAttributesList;
    }

    public Car getFitmentCar() {
        return fitmentCar;
    }

    public void setFitmentCar(Car fitmentCar) {
        this.fitmentCar = fitmentCar;
    }

    public Item getFitmentItem() {
        return fitmentItem;
    }

    public void setFitmentItem(Item fitmentItem) {
        this.fitmentItem = fitmentItem;
    }

    /*   public List<Item> getFitmentItemsList() {
            return fitmentItemsList;
        }

        public void setFitmentItemsList(List<Item> fitmentItemsList) {
            this.fitmentItemsList = fitmentItemsList;
        }
    */
    @Override
    public String toString() {
        return  "/**/Fitment{" +
                "FIT_ID=" + FIT_ID +

                /*", CAR_ID=" + CAR_ID +*/

              /*  ", ITEM_ID=" + ITEM_ID +*/
                /*"\r\n" +*/
                ", fitmentAttributesList=" + fitmentAttributesList +
                "\r\n" +
                ", fitmentCar=" + fitmentCar +

                '}';


    }
}
