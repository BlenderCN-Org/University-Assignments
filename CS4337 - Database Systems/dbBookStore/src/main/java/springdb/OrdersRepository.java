package springdb;

import db.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Integer> {

    @Query(value = "SELECT o FROM Orders o WHERE o.ono = ?1")
    Orders findByOno(@Param("ono") Integer ono);

    @Query(value = "SELECT o FROM Orders o WHERE o.userid=?1")
    List<Orders> findByUserId(@Param("userid") String userid);

}