package db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Cart")
public class Cart implements Serializable {

    @Column(name = "userid")
    private String userid;

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "qty")
    private Integer qty;

    protected Cart() {
    }

    public Cart(String userid, String isbn, Integer qty) {
        this.userid = userid;
        this.isbn = isbn;
        this.qty = qty;
    }

    public String getUserid() {
        return userid;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userid='" + userid + '\'' +
                ", isbn='" + isbn + '\'' +
                ", qty=" + qty +
                '}';
    }
}
