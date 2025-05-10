package ukim.finki.mk.lab1.service.domain;

import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(Book book);
    Optional <Book> update(Long id,Book book);
    void deleteById(Long id);
    void markRented(Long id);
    List<Book> findByNameOrAuthor(String filter);

}
