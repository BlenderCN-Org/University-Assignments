package db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Odetails")
@IdClass(Odetails.OdetailsId.class)
public class Odetails implements Serializable {

    @Id
    @Column(name = "ono")
    private Integer ono;

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "price")
    private Double price;

    public class OdetailsId implements Serializable {

        private Integer ono;
        private String isbn;

        public OdetailsId(Integer ono, String isbn) {
            this.ono = ono;
            this.isbn = isbn;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof OdetailsId)) return false;
            OdetailsId that = (OdetailsId) o;
            return Objects.equals(ono, that.ono) &&
                    Objects.equals(isbn, that.isbn);
        }

        @Override
        public int hashCode() {

            return Objects.hash(ono, isbn);
        }
    }

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
