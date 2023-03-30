package entities.attributes_links;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="item_attributes_link")
public class Item_attributes_link {

    @Id
    @Column(name="LINK_ID")
    private int LINK_ID;
    @Column(name="ITEM_ID")
    private int ITEM_ID;
    @Column(name="ITEM_ATT_ID")
    private int ITEM_ATT_ID;

    public Item_attributes_link(int LINK_ID, int ITEM_ID, int ITEM_ATT_ID) {
        this.LINK_ID = LINK_ID;
        this.ITEM_ID = ITEM_ID;
        this.ITEM_ATT_ID = ITEM_ATT_ID;
    }

    public Item_attributes_link() {

    }

    public int getLINK_ID() {
        return LINK_ID;
    }

    public void setLINK_ID(int LINK_ID) {
        this.LINK_ID = LINK_ID;
    }

    public int getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(int ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public int getITEM_ATT_ID() {
        return ITEM_ATT_ID;
    }

    public void setITEM_ATT_ID(int ITEM_ATT_ID) {
        this.ITEM_ATT_ID = ITEM_ATT_ID;
    }

}
