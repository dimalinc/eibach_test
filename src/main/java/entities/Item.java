package entities;

import entities.attributes_links.ItemPic;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="items")
public class Item {

    @Id
    @Column(name = "ITEM_ID")
    private int ITEM_ID;
    @Column(name = "ITEM_PART_NO")
    private  String ITEM_PART_NO;
    @Column(name = "ITEM_MANUFACTURER")
    private  String ITEM_MANUFACTURER;
    @Column(name = "ITEM_TYPE")
    private  String ITEM_TYPE;

    @ManyToMany
    @JoinTable(name="item_attributes_link",
            joinColumns = @JoinColumn(name="ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name="ITEM_ATT_ID"))
    private List<ItemAttribute> itemAttributeList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ITEM_ID")
    private List<ItemPic> itemPicsList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ITEM_ID")
    private List<Fitment> itemFitmentsList;



    public String getITEM_PART_NO() {
        return ITEM_PART_NO;
    }

    public void setITEM_PART_NO(String ITEM_PART_NO) {
        this.ITEM_PART_NO = ITEM_PART_NO;
    }

    public String getITEM_MANUFACTURER() {
        return ITEM_MANUFACTURER;
    }

    public void setITEM_MANUFACTURER(String ITEM_MANUFACTURER) {
        this.ITEM_MANUFACTURER = ITEM_MANUFACTURER;
    }

    public String getITEM_TYPE() {
        return ITEM_TYPE;
    }

    public void setITEM_TYPE(String ITEM_TYPE) {
        this.ITEM_TYPE = ITEM_TYPE;
    }

    public List<ItemAttribute> getItemAttributeList() {
        return itemAttributeList;
    }

    public List<ItemPic> getItemPicsList() {
        return itemPicsList;
    }


    public void setItemAttributeList(List<ItemAttribute> itemAttributeList) {
        this.itemAttributeList = itemAttributeList;
    }

    public int getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(int ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public List<Fitment> getItemFitmentsList() {
        return itemFitmentsList;
    }

    public void setItemFitmentsList(List<Fitment> itemFitmentsList) {
        this.itemFitmentsList = itemFitmentsList;
    }

    @Override
    public String toString() {
        return "\r\n\"" + '\''+
                "--------------" + '\''+
                "Item{" +
                "ITEM_ID=" + ITEM_ID + "\r\n" +
                ", ITEM_PART_NO='" + ITEM_PART_NO + '\'' +
                ", ITEM_MANUFACTURER='" + ITEM_MANUFACTURER + '\'' +
                ", ITEM_TYPE='" + ITEM_TYPE + '\'' +
             /*   "\r\n\"" + '\''+
                ", itemAttributeList=" + itemAttributeList +
                "\r\n\"" + '\''+
                ", itemFitmentsList=" + itemFitmentsList +*/
                '}';
    }
}
