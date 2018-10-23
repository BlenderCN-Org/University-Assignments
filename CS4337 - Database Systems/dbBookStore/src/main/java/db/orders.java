package db;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class orders {

    private String userid;
    private Integer ono;
    private Date recieved;
    private Date shipped;
    private String shipaddress;
    private String shipCity;
    private String shipState;
    private Integer shipZip;

}
