package service.request;

import java.net.http.HttpRequest;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface HttpRequestSender {
    Stream<CompletableFuture<String>> sendAsync(HttpRequest request);

    Stream<CompletableFuture<String>> sendAsync(Stream<HttpRequest> requests);
}
