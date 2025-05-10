package ukim.finki.mk.lab1.listeners;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ukim.finki.mk.lab1.events.AuthorChangedEvent;
import ukim.finki.mk.lab1.events.AuthorCreatedEvent;
import ukim.finki.mk.lab1.events.AuthorDeletedEvent;
import ukim.finki.mk.lab1.service.application.CountryApplicationService;

@Component
public class AuthorEventHandlers {
    private final CountryApplicationService countryApplicationService;

    public AuthorEventHandlers(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
}