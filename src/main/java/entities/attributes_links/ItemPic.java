package entities.attributes_links;

import entities.Item;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="item_pics")
public class ItemPic {

    @Id
    @Column(name="PIC_ID")
    private int PIC_ID;

    @Column(name = "PIC_URL")
    private String PIC_URL;

    @Column(name = "ITEM_ID", insertable=false, updatable=false)
    private String ITEM_ID;

    @Column(name = "FILE_NAME")
    private String FILE_NAME;

    @Column(name = "ACTUAL")
    private String ACTUAL;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item;

    public ItemPic(int PIC_ID, String PIC_URL, String ITEM_ID, String FILE_NAME, String ACTUAL) {
        this.PIC_ID = PIC_ID;
        this.PIC_URL = PIC_URL;
        this.ITEM_ID = ITEM_ID;
        this.FILE_NAME = FILE_NAME;
        this.ACTUAL = ACTUAL;
    }

    public ItemPic() {

    }

    public int getPIC_ID() {
        return PIC_ID;
    }

    public void setPIC_ID(int PIC_ID) {
        this.PIC_ID = PIC_ID;
    }

    public String getPIC_URL() {
        return PIC_URL;
    }

    public void setPIC_URL(String PIC_URL) {
        this.PIC_URL = PIC_URL;
    }

    public String getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(String ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getACTUAL() {
        return ACTUAL;
    }

    public void setACTUAL(String ACTUAL) {
        this.ACTUAL = ACTUAL;
    }

    @Override
    public String toString() {
        return "ItemPic{" +
                "PIC_ID=" + PIC_ID +
                ", PIC_URL='" + PIC_URL + '\'' +
                ", ITEM_ID='" + ITEM_ID + '\'' +
                ", FILE_NAME='" + FILE_NAME + '\'' +
                ", ACTUAL='" + ACTUAL + '\'' +
                /*", item=" + item +*/
                '}';
    }
}
