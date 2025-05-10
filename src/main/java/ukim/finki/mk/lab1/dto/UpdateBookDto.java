package ukim.finki.mk.lab1.dto;

import ukim.finki.mk.lab1.model.domain.Author;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.domain.Country;
import ukim.finki.mk.lab1.model.enums.Category;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateBookDto(Long id, String name, Category category, Long author, Integer availableCopies) {
    public static UpdateBookDto fromBook(Book book) {
        return new UpdateBookDto(book.getId(),book.getName(),book.getCategory(),book.getAuthor().getId(),book.getAvailableCopies());
    }

    public static List<UpdateBookDto> fromBook(List<Book> books) {
        return books.stream().map(UpdateBookDto::fromBook).toList();
    }
}
