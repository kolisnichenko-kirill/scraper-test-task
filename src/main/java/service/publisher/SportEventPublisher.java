package service.publisher;

import domain.SportEvent;

public interface SportEventPublisher {
    void publish(SportEvent sportEvent);
}
