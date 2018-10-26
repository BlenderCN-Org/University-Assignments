package springdb;

import db.Odetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface OdetailsRepository extends CrudRepository<Odetails, Integer> {
//    Odetails findByOno(Integer ono);

//    Odetails findByIsbn(String isbn);
}
