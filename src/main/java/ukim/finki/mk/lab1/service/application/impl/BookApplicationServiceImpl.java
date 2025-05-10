package ukim.finki.mk.lab1.service.application.impl;

import org.springframework.stereotype.Service;
import ukim.finki.mk.lab1.dto.CreateBookDto;
import ukim.finki.mk.lab1.dto.UpdateBookDto;
import ukim.finki.mk.lab1.model.domain.Author;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.enums.Category;
import ukim.finki.mk.lab1.service.application.BookApplicationService;
import ukim.finki.mk.lab1.service.domain.AuthorService;
import ukim.finki.mk.lab1.service.domain.BookService;

import java.util.List;
import java.util.Optional;
@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private  final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public List<UpdateBookDto> findAll() {
        return bookService.findAll().stream().map(UpdateBookDto::fromBook).toList();
    }

    @Override
    public Optional<UpdateBookDto> findById(Long id) {
        return (bookService.findById(id).map(UpdateBookDto::fromBook));
    }

    @Override
    public Optional<UpdateBookDto> save(CreateBookDto bookDto) {
        Optional<Author> author= authorService.findById(bookDto.author());
        if (author.isPresent()) {
            return  bookService.save(bookDto.toBook(author.get())).map(UpdateBookDto::fromBook);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UpdateBookDto> update(Long id, CreateBookDto bookDto) {
        Optional<Author> author= authorService.findById(bookDto.author());

        return bookService.update(id,bookDto.toBook(
                author.orElse(null)
        )).map(UpdateBookDto::fromBook);
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookService.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookService.deleteById(book.getId());
    }

    @Override
    public void markRented(Long id) {
        Book b=bookService.findById(id).orElse(null);
        if (b==null) {
            return;
        }
        b.setAvailableCopies(b.getAvailableCopies() == 0? 0:b.getAvailableCopies()- 1);
        bookService.save(b);
    }
    public void returnBook(Long id) {
        Book b=bookService.findById(id).orElse(null);
        if (b==null) {
            return;
        }
        b.setAvailableCopies(b.getAvailableCopies() + 1);
        bookService.save(b);
    }

    @Override
    public List<UpdateBookDto> findByNameOrAuthor(String filter) {
        List<Book> allBooks=bookService.findAll();
        List<Book> filteredBooks=allBooks.stream().filter(b->b.getName().equalsIgnoreCase(filter) || b.getAuthor().getName().equalsIgnoreCase(filter)).toList();
        return filteredBooks.stream().map(UpdateBookDto::fromBook).toList();
    }
}
