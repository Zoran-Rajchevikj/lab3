package ukim.finki.mk.lab1.dto;

import ukim.finki.mk.lab1.model.domain.Author;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.enums.Category;


public record CreateBookDto (String name, Category category, Long author, Integer availableCopies) {
    public static CreateBookDto from(Book book) {
        return  new CreateBookDto(book.getName(),book.getCategory(),book.getAuthor().getId(),book.getAvailableCopies());
    }
    public Book toBook( Author author) {
        return new Book(name,category,author,availableCopies);
    }
}
