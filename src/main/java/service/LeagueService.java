package service;

import domain.League;
import domain.Sport;
import lombok.RequiredArgsConstructor;
import service.mapper.Mapper;
import service.request.HttpRequestBuilder;
import service.request.HttpRequestSender;

import java.net.http.HttpRequest;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class LeagueService {
    private static final String LEAGUE_URI = "https://leon.bet/api-2/betline/events/all?ctag=ru-UA&league_id=%s&flags=reg";

    private final HttpRequestBuilder requestBuilder;
    private final HttpRequestSender requestSender;
    private final Mapper<League> mapper = new Mapper<>(League.class);

    public Stream<League> getLeagues(Stream<League> leagues) {
        Stream<HttpRequest> leaguesRequests = getLeaguesRequests(leagues);
        return requestSender.sendAsync(leaguesRequests)
                .map(response -> response.thenApply(mapper::parse))
                .map(CompletableFuture::join);
    }

    public Stream<League> getTopLeagues(Sport sport) {
        return sport.getRegions().stream()
                .flatMap(region -> region.getLeagues().stream().filter(League::getTop));
    }

    private Stream<HttpRequest> getLeaguesRequests(Stream<League> leagues) {
        return leagues.map(league -> requestBuilder.build(getTarget(league)));
    }

    private String getTarget(League league) {
        return String.format(LEAGUE_URI, league.getId().toString());
    }
}
