package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="car_attributes")
public class CarAttribute {

    @Id
    @Column(name="CAR_ATT_ID")
    private int CAR_ATT_ID;
    @Column(name="CAR_ATT_NAME")
    private String CAR_ATT_NAME;
    @Column(name="CAR_ATT_VALUE")
    private String CAR_ATT_VALUE;

    @ManyToMany
    @JoinTable(name="car_attributes_link",
            joinColumns = @JoinColumn(name="CAR_ATT_ID"),
            inverseJoinColumns = @JoinColumn(name="CAR_ID"))
    private List<Car> carList;

    public CarAttribute() { }

    public CarAttribute(String CAR_ATT_NAME, String CAR_ATT_VALUE) {
        this.CAR_ATT_NAME = CAR_ATT_NAME;
        this.CAR_ATT_VALUE = CAR_ATT_VALUE;
    }

    public int getCAR_ATT_ID() {
        return CAR_ATT_ID;
    }

    public void setCAR_ATT_ID(int CAR_ATT_ID) {
        this.CAR_ATT_ID = CAR_ATT_ID;
    }

    public String getCAR_ATT_NAME() {
        return CAR_ATT_NAME;
    }

    public void setCAR_ATT_NAME(String CAR_ATT_NAME) {
        this.CAR_ATT_NAME = CAR_ATT_NAME;
    }

    public String getCAR_ATT_VALUE() {
        return CAR_ATT_VALUE;
    }

    public void setCAR_ATT_VALUE(String CAR_ATT_VALUE) {
        this.CAR_ATT_VALUE = CAR_ATT_VALUE;
    }

    public List<Car> getCarList() {
        return carList;
    }

    // deleted Cars from CarList toString
    @Override
    public String toString() {
        return  "\r\n\"" + "CarAttribute{" +
                "CAR_ATT_ID=" + CAR_ATT_ID +
                ", CAR_ATT_NAME='" + CAR_ATT_NAME + '\'' +
                ", CAR_ATT_VALUE='" + CAR_ATT_VALUE + '\'' +
                '}' ;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

}
