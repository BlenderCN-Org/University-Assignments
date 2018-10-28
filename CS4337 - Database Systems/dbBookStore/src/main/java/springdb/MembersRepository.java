package springdb;

import db.Members;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends CrudRepository<Members, String> {

    @Query(value = "SELECT m FROM Members m WHERE m.userid = userid")
    Members findByUserId(@Param("userid") String userid);

}
