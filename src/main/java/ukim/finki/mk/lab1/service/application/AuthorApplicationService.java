package ukim.finki.mk.lab1.service.application;

import ukim.finki.mk.lab1.dto.CreateAuthorDto;
import ukim.finki.mk.lab1.dto.UpdateAuthorDto;
import ukim.finki.mk.lab1.model.domain.Author;
import ukim.finki.mk.lab1.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;


public interface AuthorApplicationService {
    List<UpdateAuthorDto> findAll();
    Optional<UpdateAuthorDto> findById(Long id);
    Optional<UpdateAuthorDto> save(CreateAuthorDto authorDto);
    Optional<UpdateAuthorDto> update(Long id, CreateAuthorDto authorDto);
    void deleteById(Long id);
    List<BooksPerAuthorView> findAllBooksPerAuthor();
    BooksPerAuthorView findBooksPerAuthorById(Long id);
    void refreshMaterializedView();
}
