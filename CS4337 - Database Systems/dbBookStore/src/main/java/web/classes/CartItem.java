package web.classes;

import java.util.Objects;

public class CartItem {

    private String isbn;
    private String title;
    private Double price;
    private Integer qty;
    private Double total;

    public CartItem(String isbn, String title, Double price, Integer qty) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.qty = qty;
        this.total = qty*price;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return String.format("%.2f", price);
    }
    public Double getPriceDouble() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return String.format("%.2f", total);
    }

    public Double getTotalDouble() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(isbn, cartItem.isbn) &&
                Objects.equals(title, cartItem.title) &&
                Objects.equals(price, cartItem.price) &&
                Objects.equals(qty, cartItem.qty) &&
                Objects.equals(total, cartItem.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, price, qty, total);
    }
}
