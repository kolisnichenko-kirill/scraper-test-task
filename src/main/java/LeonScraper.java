import domain.SportEvent;
import lombok.RequiredArgsConstructor;
import service.LeagueService;
import service.SportEventService;
import service.SportService;
import service.publisher.SportEventPublisher;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class LeonScraper {
    private final SportService sportService;
    private final LeagueService leagueService;
    private final SportEventService eventService;
    private final SportEventPublisher publisher;

    public void scrape() {
        Stream<Optional<SportEvent>> sportEvents = sportService.getSports()
                .map(leagueService::getTopLeagues)
                .flatMap(leagueService::getLeagues)
                .map(eventService::getFirstEvent);

        Stream<Long> eventsIds = eventService.getEventsIds(sportEvents);

        eventService.getEvents(eventsIds)
                .forEach(publisher::publish);
    }
}
