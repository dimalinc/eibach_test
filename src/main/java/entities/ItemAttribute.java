package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="item_attributes")
public class ItemAttribute {

    @Id
    @Column(name="ITEM_ATT_ID")
    private int ITEM_ATT_ID;

    @Column(name = "ITEM_ATT_NAME")
    private String ITEM_ATT_NAME;

    @Column(name = "ITEM_ATT_VALUE")
    private String ITEM_ATT_VALUE;

    @ManyToMany/*(fetch = FetchType.EAGER)*/
    @JoinTable(name="item_attributes_link",
            joinColumns = @JoinColumn(name="ITEM_ATT_ID"),
            inverseJoinColumns = @JoinColumn(name="ITEM_ID"))
    private List<Item> itemsList;

    public int getITEM_ATT_ID() {
        return ITEM_ATT_ID;
    }

    public void setITEM_ATT_ID(int ITEM_ATT_ID) {
        this.ITEM_ATT_ID = ITEM_ATT_ID;
    }

    public String getITEM_ATT_NAME() {
        return ITEM_ATT_NAME;
    }

    public void setITEM_ATT_NAME(String ITEM_ATT_NAME) {
        this.ITEM_ATT_NAME = ITEM_ATT_NAME;
    }

    public String getITEM_ATT_VALUE() {
        return ITEM_ATT_VALUE;
    }

    public void setITEM_ATT_VALUE(String ITEM_ATT_VALUE) {
        this.ITEM_ATT_VALUE = ITEM_ATT_VALUE;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public String toString() {
        return "\r\n" + "ItemAttribute{" +
                "ITEM_ATT_ID=" + ITEM_ATT_ID +
                ", ITEM_ATT_NAME='" + ITEM_ATT_NAME + '\'' +
                ", ITEM_ATT_VALUE='" + ITEM_ATT_VALUE + '\'' +

                '}';
    }
}
