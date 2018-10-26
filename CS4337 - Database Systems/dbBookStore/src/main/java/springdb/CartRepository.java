package springdb;

import db.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
//    Cart findByUserId(String userid);
//    Cart findByIsbn(String isbn);
}
