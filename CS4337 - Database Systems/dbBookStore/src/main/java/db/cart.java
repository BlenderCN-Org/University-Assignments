package db;

import javax.persistence.Entity;

@Entity
public class cart {
    private String userid;
    private String isbn;
    private Integer qty;
}
