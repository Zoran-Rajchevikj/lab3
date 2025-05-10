package ukim.finki.mk.lab1.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ukim.finki.mk.lab1.service.application.AuthorApplicationService;

@Component
public class ScheduledTasks {
    private final AuthorApplicationService authorApplicationService;

    public ScheduledTasks(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }
    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
        authorApplicationService.refreshMaterializedView();
    }
}
