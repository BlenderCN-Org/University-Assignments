package db;

import javax.persistence.Entity;

@Entity
public class books {

    private Long isbn;
    private String author;
    private String title;
    private Double price;
    private String subject;


}
