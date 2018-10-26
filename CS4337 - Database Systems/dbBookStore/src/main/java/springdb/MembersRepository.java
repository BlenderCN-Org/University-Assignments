package springdb;

import db.Members;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface MembersRepository extends CrudRepository<Members, String> {
//    Members findByUserId(String userid);
}
