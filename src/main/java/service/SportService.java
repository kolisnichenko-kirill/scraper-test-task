package service;

import domain.Sport;
import lombok.RequiredArgsConstructor;
import service.mapper.Mapper;
import service.request.HttpRequestBuilder;
import service.request.HttpRequestSender;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SportService {
    private static final String SPORT_URI = "https://leon.bet/api-2/betline/sports?ctag=ru-UA&flags=urlv2";

    private final HttpRequestBuilder requestBuilder;
    private final HttpRequestSender requestSender;
    private final Mapper<Sport[]> mapper = new Mapper<>(Sport[].class);

    public Stream<Sport> getSports() {
        HttpRequest sportRequest = requestBuilder.build(SPORT_URI);
        return requestSender.sendAsync(sportRequest)
                .map(response -> response.thenApply(mapper::parse))
                .map(CompletableFuture::join)
                .flatMap(Arrays::stream);
    }
}
