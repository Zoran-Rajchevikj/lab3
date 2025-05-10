package ukim.finki.mk.lab1.events;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;
import ukim.finki.mk.lab1.model.domain.Author;

import java.time.LocalDateTime;

@Getter
public class AuthorCreatedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public AuthorCreatedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}