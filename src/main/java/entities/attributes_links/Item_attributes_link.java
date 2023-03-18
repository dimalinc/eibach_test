package entities.attributes_links;

public class Item_attributes_link {

    private int LINK_ID;
    private int ITEM_ID;
    private int ITEM_ATT_ID;

    public Item_attributes_link(int LINK_ID, int ITEM_ID, int ITEM_ATT_ID) {
        this.LINK_ID = LINK_ID;
        this.ITEM_ID = ITEM_ID;
        this.ITEM_ATT_ID = ITEM_ATT_ID;
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
