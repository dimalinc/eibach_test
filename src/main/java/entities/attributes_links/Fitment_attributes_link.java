package entities.attributes_links;

public class Fitment_attributes_link {

    private int LINK_ID;
    private int FIT_ID;
    private int FIT_ATT_ID;

    public Fitment_attributes_link(int LINK_ID, int FIT_ID, int FIT_ATT_ID) {
        this.LINK_ID = LINK_ID;
        this.FIT_ID = FIT_ID;
        this.FIT_ATT_ID = FIT_ATT_ID;
    }

    public int getLINK_ID() {
        return LINK_ID;
    }

    public void setLINK_ID(int LINK_ID) {
        this.LINK_ID = LINK_ID;
    }

    public int getFIT_ID() {
        return FIT_ID;
    }

    public void setFIT_ID(int FIT_ID) {
        this.FIT_ID = FIT_ID;
    }

    public int getFIT_ATT_ID() {
        return FIT_ATT_ID;
    }

    public void setFIT_ATT_ID(int FIT_ATT_ID) {
        this.FIT_ATT_ID = FIT_ATT_ID;
    }
}
