package db;

import oracle.sql.TIMESTAMP;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Orders")
public class Orders implements Serializable {

    @Column(name = "userid")
    private String userid;

    @Id
    @Column(name = "ono")
    private Integer ono;

    @Column(name = "received")
    @Temporal(TemporalType.DATE)
    private Date received;

    @Column(name = "shipped")
    @Temporal(TemporalType.DATE)
    private Date shipped;

    @Column(name = "shipAddress")
    private String shipAddress;

    @Column(name = "shipCity")
    private String shipCity;

    @Column(name = "shipState")
    private String shipState;

    @Column(name = "shipZip")
    private Integer shipZip;


    protected Orders() {
    }

    public Orders(String userid, Integer ono, Date received, Date shipped, String shipAddress, String shipCity, String shipState, Integer shipZip) {
        this.userid = userid;
        this.ono = ono;
        this.received = received;
        this.shipped = shipped;
        this.shipAddress = shipAddress;
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

    public String getshipAddress() {
        return shipAddress;
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
                ", shipAddress='" + shipAddress + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipState='" + shipState + '\'' +
                ", shipZip=" + shipZip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(userid, orders.userid) &&
                Objects.equals(ono, orders.ono) &&
                Objects.equals(received, orders.received) &&
                Objects.equals(shipped, orders.shipped) &&
                Objects.equals(shipAddress, orders.shipAddress) &&
                Objects.equals(shipCity, orders.shipCity) &&
                Objects.equals(shipState, orders.shipState) &&
                Objects.equals(shipZip, orders.shipZip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, ono, received, shipped, shipAddress, shipCity, shipState, shipZip);
    }
}
