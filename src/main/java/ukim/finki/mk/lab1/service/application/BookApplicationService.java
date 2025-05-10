package ukim.finki.mk.lab1.service.application;

import ukim.finki.mk.lab1.dto.CreateBookDto;
import ukim.finki.mk.lab1.dto.UpdateBookDto;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<UpdateBookDto> findAll();
    Optional<UpdateBookDto> findById(Long id);
    Optional<UpdateBookDto> save(CreateBookDto bookDto);
    Optional <UpdateBookDto> update(Long id, CreateBookDto bookDto);
    void deleteById(Long id);
    void markRented(Long id);
    List<UpdateBookDto> findByNameOrAuthor(String filter);
}
