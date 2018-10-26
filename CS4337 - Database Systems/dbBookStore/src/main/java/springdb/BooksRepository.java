package springdb;

import db.Books;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public interface BooksRepository extends CrudRepository<Books, String> {

    @Query(value = "SELECT b FROM Books b WHERE b.isbn=isbn")
    Books findByIsbn(@Param("isbn") String isbn);

    // SELECT * is implicit in the query
    @Query(value = "FROM Books b WHERE b.subject=subject")
    List<Books> findBySubject(@Param("subject") String subject);

}

