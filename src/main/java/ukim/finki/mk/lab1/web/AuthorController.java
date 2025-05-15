package ukim.finki.mk.lab1.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukim.finki.mk.lab1.dto.CreateAuthorDto;
import ukim.finki.mk.lab1.dto.UpdateAuthorDto;
import ukim.finki.mk.lab1.dto.UpdateCountryDto;
import ukim.finki.mk.lab1.model.domain.Author;
import ukim.finki.mk.lab1.projections.AuthorNamesProjection;
import ukim.finki.mk.lab1.repository.AuthorRepository;
import ukim.finki.mk.lab1.service.application.AuthorApplicationService;
import ukim.finki.mk.lab1.service.application.CountryApplicationService;
import ukim.finki.mk.lab1.service.domain.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;
    private final CountryApplicationService countryApplicationService;
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorApplicationService authorApplicationService, CountryApplicationService countryApplicationService, AuthorRepository authorRepository) {
        this.authorApplicationService = authorApplicationService;

        this.countryApplicationService = countryApplicationService;
        this.authorRepository = authorRepository;
    }

    @Operation(summary = "Get a list of all authors", description = "Returns a list of all authors in the system.")
    @GetMapping
//    public List<UpdateAuthorDto> listAll() {
//        return authorApplicationService.findAll();
//    }
    public ResponseEntity<?> listAllAuthors() {
            return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findAll());
        }
    @GetMapping("/{id}")
    public ResponseEntity<UpdateAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id).map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new author", description = "Creates a new author in the system using the provided data.")
    @PostMapping("/add")
//    public ResponseEntity<UpdateAuthorDto> addAuthor(@RequestBody CreateAuthorDto createAuthorDto) {
//        return authorApplicationService.save(createAuthorDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
    public ResponseEntity<?> addAuthor(@RequestBody CreateAuthorDto createAuthorDto) {
        return ResponseEntity.ok(authorApplicationService.save(createAuthorDto));
    }

    @Operation(summary = "Edit an existing author", description = "Updates the details of an existing author by their ID.")
    @PutMapping("/edit/{id}")
//    public ResponseEntity<UpdateAuthorDto> editAuthor(@PathVariable Long id, @RequestBody CreateAuthorDto createAuthorDto) {
//        return authorApplicationService.update(id, createAuthorDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
    public ResponseEntity<?> editAuthor(@PathVariable Long id, @RequestBody CreateAuthorDto createAuthorDto) {
        return ResponseEntity.ok(authorApplicationService.update(id,createAuthorDto));
    }

    @Operation(summary = "Delete an author", description = "Deletes an author by their ID from the system.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        authorApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-country")
    @Operation(summary = "returns a list of authors by countries")
    public ResponseEntity<?> findAllAuthorsByCountry() {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findAllAuthorsPerCountry());
    }

    @GetMapping("/by-country/{id}")
    @Operation(summary = "returns an author by country")
    public ResponseEntity<?> findAuthorByCountry(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findAuthorsPerCountryById(id));
    }

    @GetMapping("/names")
    public ResponseEntity<List<AuthorNamesProjection>> getAuthorNames() {
        List<AuthorNamesProjection> authorNames = authorRepository.findAllProjectedBy(Sort.by("name"));
        return ResponseEntity.ok(authorNames);
    }
}
