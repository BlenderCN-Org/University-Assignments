package db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Cart")
@IdClass(Cart.CartId.class)
public class Cart implements Serializable {

    @Id
    @Column(name = "userid")
    private String userid;

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "qty")
    private Integer qty;

    public class CartId implements Serializable {

        private String userid;
        private String isbn;

        public CartId() {
            
        }

        public CartId(String userid, String isbn) {
            this.userid = userid;
            this.isbn = isbn;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CartId)) return false;
            CartId cartId = (CartId) o;
            return Objects.equals(userid, cartId.userid) &&
                    Objects.equals(isbn, cartId.isbn);
        }

        @Override
        public int hashCode() {

            return Objects.hash(userid, isbn);
        }
    }

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
