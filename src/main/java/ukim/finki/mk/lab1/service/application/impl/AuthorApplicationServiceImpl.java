package ukim.finki.mk.lab1.service.application.impl;

import org.springframework.stereotype.Service;
import ukim.finki.mk.lab1.dto.CreateAuthorDto;
import ukim.finki.mk.lab1.dto.UpdateAuthorDto;
import ukim.finki.mk.lab1.model.domain.Country;
import ukim.finki.mk.lab1.model.views.BooksPerAuthorView;
import ukim.finki.mk.lab1.repository.BooksPerAuthorViewRepository;
import ukim.finki.mk.lab1.service.application.AuthorApplicationService;
import ukim.finki.mk.lab1.service.domain.AuthorService;
import ukim.finki.mk.lab1.service.domain.CountryService;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }


    @Override
    public List<UpdateAuthorDto> findAll() {
//        return UpdateAuthorDto.from(authorService.findAll());
        return  authorService.findAll().stream().map(UpdateAuthorDto::from).toList();
    }

    @Override
    public Optional<UpdateAuthorDto> findById(Long id) {
        return authorService.findById(id).map(UpdateAuthorDto::from);
    }

    @Override
    public Optional<UpdateAuthorDto> save(CreateAuthorDto authorDto) {
        Optional<Country> country = countryService.findById(authorDto.country());
        if(country.isPresent()) {
            return authorService.save(authorDto.toAuthor(country.get())).map(UpdateAuthorDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UpdateAuthorDto> update(Long id, CreateAuthorDto authorDto) {
        Optional<Country> country = countryService.findById(authorDto.country());
        return authorService.update(id,authorDto.toAuthor(country.orElse(null))).map(UpdateAuthorDto::from);
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }

    @Override
    public List<BooksPerAuthorView> findAllBooksPerAuthor() {
        return booksPerAuthorViewRepository.findAll();
    }

    @Override
    public BooksPerAuthorView findBooksPerAuthorById(Long id) {
        return booksPerAuthorViewRepository.findById(id).orElseThrow(()-> new RuntimeException("BooksPerAuthor not found"));
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();
    }
}
