package springdb;

import db.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Integer> {
//    Orders findByOno(Integer ono);
}