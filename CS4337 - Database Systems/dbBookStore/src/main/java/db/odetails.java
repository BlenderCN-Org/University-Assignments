package db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Odetails")
public class Odetails implements Serializable {

    @Id
    @Column(name = "ono")
    private Integer ono;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "price")
    private Double price;


    protected Odetails() {
    }

    public Odetails(Integer ono, String isbn, Integer qty, Double price) {
        this.ono = ono;
        this.isbn = isbn;
        this.qty = qty;
        this.price = price;
    }

    public Integer getOno() {
        return ono;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getQty() {
        return qty;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Odetails{" +
                "ono=" + ono +
                ", isbn='" + isbn + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
