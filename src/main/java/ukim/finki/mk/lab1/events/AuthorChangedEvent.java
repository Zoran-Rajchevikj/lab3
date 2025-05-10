package ukim.finki.mk.lab1.events;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ukim.finki.mk.lab1.model.domain.Author;

import java.time.LocalDateTime;

@Getter
public class AuthorChangedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public AuthorChangedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}