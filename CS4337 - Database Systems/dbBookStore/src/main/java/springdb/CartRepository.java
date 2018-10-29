package springdb;

import db.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {

    @Query(value = "SELECT c FROM Cart c WHERE c.isbn=isbn")
    Cart getCartByIsbn(@Param("isbn") String isbn);

}
