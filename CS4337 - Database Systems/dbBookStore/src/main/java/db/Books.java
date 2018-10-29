package db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Books")
public class Books implements Serializable {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "subject")
    private String subject;


    protected Books() {
    }

    public Books(String isbn, String author, String title, Double price, String subject) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.price = price;
        this.subject = subject;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return String.format("%.2f", price);
    }

    public String getSubject() {
        return subject;
    }


    @Override
    public String toString() {
        return "Books{" +
                "isbn=" + isbn +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Books)) return false;
        Books books = (Books) o;
        return Objects.equals(isbn, books.isbn) &&
                Objects.equals(author, books.author) &&
                Objects.equals(title, books.title) &&
                Objects.equals(price, books.price) &&
                Objects.equals(subject, books.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, author, title, price, subject);
    }
}
