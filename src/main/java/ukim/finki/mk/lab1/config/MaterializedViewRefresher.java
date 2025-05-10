package ukim.finki.mk.lab1.config;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import ukim.finki.mk.lab1.repository.AuthorsPerCountryViewRepository;
import ukim.finki.mk.lab1.repository.BooksPerAuthorViewRepository;


@Component
public class MaterializedViewRefresher {
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;

    public MaterializedViewRefresher(BooksPerAuthorViewRepository booksPerAuthorViewRepository, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }

    @PostConstruct
    public void init() {
        booksPerAuthorViewRepository.refreshMaterializedView();
        authorsPerCountryViewRepository.refreshMaterializedView();
    }
}