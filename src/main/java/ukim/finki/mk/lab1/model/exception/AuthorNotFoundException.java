package ukim.finki.mk.lab1.model.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super(String.format("Author with id %s not found: " , id));
    }
}
