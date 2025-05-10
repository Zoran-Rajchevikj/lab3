package ukim.finki.mk.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukim.finki.mk.lab1.model.domain.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
