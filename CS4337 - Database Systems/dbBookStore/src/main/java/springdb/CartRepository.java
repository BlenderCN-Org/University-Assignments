package springdb;

import db.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {

    @Query(value = "SELECT c FROM Cart c WHERE c.isbn=?1")
    Cart getCartByIsbn(@Param("isbn") String isbn);

    @Query(value = "SELECT c FROM Cart c WHERE c.userid=?1")
    List<Cart> getCartByUserId(@Param("userid") String userid);

}
