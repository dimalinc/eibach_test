package entities.objects;

import entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbObject {

   private Item item;
    List<ItemAttribute> itemAttributeList;
    List<Fitment> fitmentList;
    List<FitmentAttribute> fitmentAttributeList;
    Map<Fitment, List<FitmentAttribute>> fitment_fitmentAttribute_Map = new HashMap<>();
    List<Car> carList = new ArrayList<>();
    Map<Car, List<CarAttribute>> car_carAttribute_Map = new HashMap<>() ;

    public DbObject(Item item) {
        this.item = item;
        setItemAttributeList(item.getItemAttributeList());
        setFitmentList(item.getItemFitmentsList());

        for (Fitment fitment : fitmentList) {
            Car car = fitment.getFitmentCar();
            carList.add(car);
          //  car_carAttribute_Map.put(car, car.getCarAttributeList());
          //  fitment_fitmentAttribute_Map.put(fitment, fitment.getFitmentAttributesList());
        }

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<ItemAttribute> getItemAttributeList() {
        return itemAttributeList;
    }

    public void setItemAttributeList(List<ItemAttribute> itemAttributeList) {
        this.itemAttributeList = itemAttributeList;
    }

    public List<Fitment> getFitmentList() {
        return fitmentList;
    }

    public void setFitmentList(List<Fitment> fitmentList) {
        this.fitmentList = fitmentList;
    }

    public List<FitmentAttribute> getFitmentAttributeList() {
        return fitmentAttributeList;
    }

    public void setFitmentAttributeList(List<FitmentAttribute> fitmentAttributeList) {
        this.fitmentAttributeList = fitmentAttributeList;
    }

    public Map<Fitment, List<FitmentAttribute>> getFitment_fitmentAttribute_Map() {
        return fitment_fitmentAttribute_Map;
    }

    public void setFitment_fitmentAttribute_Map(Map<Fitment, List<FitmentAttribute>> fitment_fitmentAttribute_Map) {
        this.fitment_fitmentAttribute_Map = fitment_fitmentAttribute_Map;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public Map<Car, List<CarAttribute>> getCar_carAttribute_Map() {
        return car_carAttribute_Map;
    }

    public void setCar_carAttribute_Map(Map<Car, List<CarAttribute>> car_carAttribute_Map) {
        this.car_carAttribute_Map = car_carAttribute_Map;
    }


}
