package ru.lisenkova;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "SELECT c FROM Book c WHERE Lower(c.title) LIKE lower(CONCAT('%', :keyword, '%')) OR lower(c.author) LIKE lower(CONCAT('%', :keyword, '%'))")// OR lower(c.isbn) LIKE lower(CONCAT('%', :keyword, '%'))")
        //        + " OR c.birthdate LIKE CONCAT('%', :keyword, '%')")
    List<Book> search(@Param("keyword") String keyword);
    List<Book> findAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}
