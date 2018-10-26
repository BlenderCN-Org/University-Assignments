package db;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Orders")
public class Orders {

    @Column(name = "userid")
    private String userid;

    @Id
    @Column(name = "ono")
    private Integer ono;

    @Column(name = "received")
    private Date received;

    @Column(name = "shipped")
    private Date shipped;

    @Column(name = "shipaddress")
    private String shipaddress;

    @Column(name = "shipCity")
    private String shipCity;

    @Column(name = "shipState")
    private String shipState;

    @Column(name = "shipZip")
    private Integer shipZip;


    protected Orders() {
    }

    public Orders(String userid, Integer ono, Date received, Date shipped, String shipaddress, String shipCity, String shipState, Integer shipZip) {
        this.userid = userid;
        this.ono = ono;
        this.received = received;
        this.shipped = shipped;
        this.shipaddress = shipaddress;
        this.shipCity = shipCity;
        this.shipState = shipState;
        this.shipZip = shipZip;
    }

    public String getUserid() {
        return userid;
    }

    public Integer getOno() {
        return ono;
    }

    public Date getReceived() {
        return received;
    }

    public Date getShipped() {
        return shipped;
    }

    public String getShipaddress() {
        return shipaddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public Integer getShipZip() {
        return shipZip;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "userid='" + userid + '\'' +
                ", ono=" + ono +
                ", recieved=" + received +
                ", shipped=" + shipped +
                ", shipaddress='" + shipaddress + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipState='" + shipState + '\'' +
                ", shipZip=" + shipZip +
                '}';
    }

}
