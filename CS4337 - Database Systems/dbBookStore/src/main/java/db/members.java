package db;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Members implements Serializable {

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "userid")
    private String userid;

    @Column(name = "password")
    private String password;

    @Column(name = "creditcardtype")
    private String creditcardtype;

    @Column(name = "creditcardnumber")
    private String creditcardnumber;


    protected Members() {
    }


    public Members(String fname, String lname, String address, String city, String state, Integer zip, Long phone, String email, String userid, String password, String creditcardtype, String creditcardnumber) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.userid = userid;
        this.password = password;
        this.creditcardtype = creditcardtype;
        this.creditcardnumber = creditcardnumber;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Integer getZip() {
        return zip;
    }

    public Long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getCreditcardtype() {
        return creditcardtype;
    }

    public String getCreditcardnumber() {
        return creditcardnumber;
    }

    @Override
    public String toString() {
        return "Members{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", creditcardtype='" + creditcardtype + '\'' +
                ", creditcardnumber='" + creditcardnumber + '\'' +
                '}';
    }

}
