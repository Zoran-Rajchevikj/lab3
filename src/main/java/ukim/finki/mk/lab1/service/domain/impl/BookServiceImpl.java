package ukim.finki.mk.lab1.service.domain.impl;

import org.springframework.stereotype.Service;
import ukim.finki.mk.lab1.model.domain.Author;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.enums.Category;
import ukim.finki.mk.lab1.repository.AuthorRepository;
import ukim.finki.mk.lab1.repository.BookRepository;
import ukim.finki.mk.lab1.service.domain.AuthorService;
import ukim.finki.mk.lab1.service.domain.BookService;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorService authorService, AuthorRepository authorRepository1) {
        this.bookRepository = bookRepository;


        this.authorRepository = authorRepository1;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, Book book) {

        return bookRepository.findById(id).map(existing -> {
            // Update name if it's not null
            if (book.getName() != null) {
                existing.setName(book.getName());
            }

            // Update category if it's not null
            if (book.getCategory() != null) {
                existing.setCategory(book.getCategory());
            }

            // Update author if it's not null
            if (book.getAuthor() != null) {
                existing.setAuthor(book.getAuthor());
            }

            // Update availableCopies if it's not null
            if (book.getAvailableCopies() != null) {
                existing.setAvailableCopies(book.getAvailableCopies());
            }

            // Save the updated book
            return bookRepository.save(existing);
        });
    }

//    @Override
//    public Book save(String name, Category category, Long authorId, Integer availableCopies) {
//        Author a = authorRepository.findById(authorId).orElse(null);
//        if (a == null) {
//            return null;
//        }
//
//        return bookRepository.save(new Book(name,category,a,availableCopies));
//    }
//
//    @Override
//    public Book update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
//        Book b=bookRepository.findById(id).orElse(null);
//        Author a=authorRepository.findById(authorId)
//                .orElse(null);
//
//        if (b==null || a==null) {
//            return null;
//        }
//        b.setName(name);
//        b.setCategory(category);
//        b.setAuthor(a);
//        b.setAvailableCopies(availableCopies);
//
//        return bookRepository.save(b);
//    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void markRented(Long id) {
        Book b=bookRepository.findById(id).orElse(null);
        if (b==null) {
            return;
        }
        b.setAvailableCopies(b.getAvailableCopies() == 0? 0:b.getAvailableCopies()- 1);
        bookRepository.save(b);
    }

    @Override
    public List<Book> findByNameOrAuthor(String filter) {
        List<Book> allBooks=bookRepository.findAll();
        List<Book> filteredBooks=allBooks.stream().filter(b->b.getName().equalsIgnoreCase(filter) || b.getAuthor().getName().equalsIgnoreCase(filter)).toList();
        return filteredBooks;
    }
}
