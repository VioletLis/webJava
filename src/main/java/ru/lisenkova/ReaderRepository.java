package ru.lisenkova;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    @Query(value = "SELECT c FROM Reader c WHERE Lower(c.name) LIKE lower(CONCAT('%', :keyword, '%')) OR c.surname LIKE CONCAT('%', :keyword, '%')")
    //        + " OR c.birthdate LIKE CONCAT('%', :keyword, '%')")
    List<Reader> search(@Param("keyword") String keyword);
    List<Reader> findAll();
    List<Reader> findBySurname(String surname);
}
