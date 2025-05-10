package ukim.finki.mk.lab1.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukim.finki.mk.lab1.dto.CreateBookDto;
import ukim.finki.mk.lab1.dto.UpdateBookDto;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.enums.Category;
import ukim.finki.mk.lab1.service.application.AuthorApplicationService;
import ukim.finki.mk.lab1.service.application.BookApplicationService;
import ukim.finki.mk.lab1.service.domain.BookService;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping({"/", "/api/books"})
public class BookController {
    private final BookApplicationService bookApplicationService;
    private final AuthorApplicationService authorApplicationService;

    public BookController(BookApplicationService bookApplicationService, AuthorApplicationService authorApplicationService) {

        this.bookApplicationService = bookApplicationService;
        this.authorApplicationService = authorApplicationService;
    }

    @Operation(summary = "Get all books", description = "Returns a list of all books.")
    @GetMapping
//    public List<UpdateBookDto> listAll() {
//        return bookApplicationService.findAll();
//    }
    public ResponseEntity<?> listAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookApplicationService.findAll());
    }

    @Operation(summary = "Add a new book", description = "Creates a new book in the system using the provided data.\n Categories:")
    @PostMapping("/add")
//    public ResponseEntity<UpdateBookDto> add(@RequestBody CreateBookDto createBookDto) {
//        return bookApplicationService.save(createBookDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
    public ResponseEntity<?> addBook(@RequestBody CreateBookDto bookDto) {
        return ResponseEntity.ok(bookApplicationService.save(bookDto));
    }

    @Operation(summary = "Edit an existing book", description = "Updates the details of an existing book by its ID.")
    @PutMapping("/edit/{id}")
//    public ResponseEntity<UpdateBookDto> update(@PathVariable Long id, @RequestBody CreateBookDto createBookDto) {
//        return bookApplicationService.update(id, createBookDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
    public ResponseEntity<?> updateBook(@RequestBody CreateBookDto bookDto, @PathVariable Long id) {
        return ResponseEntity.ok(bookApplicationService.update(id, bookDto));
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID from the system.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Mark book as rented", description = "Marks a book as rented by its ID, reducing its available copies.")
    @PutMapping("/rent/{id}")
    public ResponseEntity<Void> markAsRented(@PathVariable Long id) {
        bookApplicationService.markRented(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Filter books by name or author", description = "Returns a list of books that match the given name or author filter.")
    @GetMapping("/filter")
    public ResponseEntity<List<UpdateBookDto>> filterByNameOrAuthor(@RequestParam String filter) {
        return ResponseEntity.ok(bookApplicationService.findByNameOrAuthor(filter));
    }
    //api/books/by-author
    @Operation(summary = "List number of books per author for every author")
    @GetMapping("/by-author")
    public ResponseEntity<?> findAllBooksByAuthor() {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findAllBooksPerAuthor());
    }
    @Operation(summary = "List number of books for a given author")
    @GetMapping("/by-author/{id}")
    public ResponseEntity<?> findAllBooksByAuthor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findBooksPerAuthorById(id));
    }

}
