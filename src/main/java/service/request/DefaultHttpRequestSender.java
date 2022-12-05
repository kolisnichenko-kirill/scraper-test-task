package service.request;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class DefaultHttpRequestSender implements HttpRequestSender {
    private final static Integer MAX_THREADS = 3;
    private final static Long CONNECTION_TIMEOUT_SEC = 10L;

    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
    private final HttpClient httpClient = HttpClient.newBuilder()
            .executor(executorService)
            .connectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT_SEC))
            .build();

    @Override
    public Stream<CompletableFuture<String>> sendAsync(HttpRequest request) {
        return sendAsync(Stream.of(request));
    }

    @Override
    public Stream<CompletableFuture<String>> sendAsync(Stream<HttpRequest> requests) {
        return requests
                .map(request -> httpClient
                        .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body)
                );
    }
}
