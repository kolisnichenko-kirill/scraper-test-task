package service;

import domain.League;
import domain.SportEvent;
import lombok.RequiredArgsConstructor;
import service.mapper.Mapper;
import service.request.HttpRequestBuilder;
import service.request.HttpRequestSender;

import java.net.http.HttpRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SportEventService {
    private static final String EVENT_URI = "https://leon.bet/api-2/betline/event/all?ctag=ru-UA&eventId=%s&flags=reg";

    private final HttpRequestBuilder requestBuilder;
    private final HttpRequestSender requestSender;
    private final Mapper<SportEvent> mapper = new Mapper<>(SportEvent.class);

    public Optional<SportEvent> getFirstEvent(League league) {
        return league.getEvents().stream().findFirst();
    }

    public Stream<SportEvent> getEvents(Stream<Long> eventsIds) {
        Stream<HttpRequest> eventsRequests = getEventsRequests(eventsIds);

        return requestSender.sendAsync(eventsRequests)
                .map(response -> response.thenApply(mapper::parse))
                .map(CompletableFuture::join);
    }

    public Stream<Long> getEventsIds(Stream<Optional<SportEvent>> events) {
        List<Long> eventsIds = new LinkedList<>();
        events.forEach(optional -> optional.ifPresent(sportEvent -> eventsIds.add(sportEvent.getId())));
        return eventsIds.stream();
    }

    private Stream<HttpRequest> getEventsRequests(Stream<Long> eventsIds) {
        return eventsIds.map(eventId -> requestBuilder.build(String.format(EVENT_URI, eventId.toString())));
    }
}
