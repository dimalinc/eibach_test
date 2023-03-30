package entities.objects;

import entities.*;
import entities.attributes_links.Car_attributes_link;
import entities.attributes_links.Fitment_attributes_link;
import entities.attributes_links.ItemPic;
import entities.attributes_links.Item_attributes_link;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbObject {

   private Item item;
    List<ItemAttribute> itemAttributeList=new ArrayList<>();
    List<Fitment> fitmentList=new ArrayList<>();
    List<FitmentAttribute> fitmentAttributeList=new ArrayList<>();
    Map<Fitment, List<FitmentAttribute>> fitment_fitmentAttribute_Map = new HashMap<>();
    List<Car> carList = new ArrayList<>();
    List<CarAttribute> carAttributeList = new ArrayList<>();
    Map<Car, List<CarAttribute>> car_carAttribute_Map = new HashMap<>() ;
    List<ItemPic> itemPicsList=new ArrayList<>();

    public DbObject(Item item, List<ItemAttribute> allItemAttributeList,
                        List<Car> allCarList, List<CarAttribute> allCarAttributeList,
                        List<Fitment> allFitmentList, List<FitmentAttribute> allFitmentAttributeList,
                        List<Item_attributes_link> allItemAttributesLinkList,
                        List<Fitment_attributes_link> allfitmentAttributesLinkList,
                        List<Car_attributes_link> allCarAttributesLinkList,
                        List<ItemPic> allItemPicsList) {

        this.item=item;

        for (Item_attributes_link item_attributes_link : allItemAttributesLinkList)
            for (ItemAttribute itemAttribute : allItemAttributeList)
                if ((this.item.getITEM_ID() == item_attributes_link.getITEM_ID())
                        && (itemAttribute.getITEM_ATT_ID() == item_attributes_link.getITEM_ATT_ID()))
                    this.itemAttributeList.add(itemAttribute);

        for (Fitment fitment:allFitmentList)
            if(this.item.getITEM_ID()==fitment.getITEM_ID())
                this.fitmentList.add(fitment);

        if(fitmentList!=null) {
            for (Car car : allCarList)
                for (Fitment fitment : fitmentList)
                    if (fitment.getCAR_ID() == car.getCAR_ID())
                        this.carList.add(car);

            for (Fitment_attributes_link fitment_attributes_link : allfitmentAttributesLinkList)
                for (FitmentAttribute fitmentAttribute : allFitmentAttributeList)
                    if (fitmentAttribute.getFIT_ATT_ID() == fitment_attributes_link.getFIT_ID())
                        this.fitmentAttributeList.add(fitmentAttribute);

            for (Car_attributes_link car_attributes_link : allCarAttributesLinkList)
                for (CarAttribute carAttribute : allCarAttributeList)
                    if (carAttribute.getCAR_ATT_ID() == car_attributes_link.getCAR_ATT_ID())
                        this.carAttributeList.add(carAttribute);

            for (ItemPic itemPic:allItemPicsList) {
                if (itemPic.getITEM_ID()==item.getITEM_ID())
                itemPicsList.add(itemPic);
            }
        }
    }

    public DbObject(Item item) {
        this.item = item;
        this.item.setITEM_ID(item.getITEM_ID());
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
