package service.request;

import java.net.URI;
import java.net.http.HttpRequest;

public class DefaultHttpRequestBuilder implements HttpRequestBuilder {
    public static final String X_APP_LANGUAGE = "x-app-language";
    public static final String RU_UA = "ru_UA";

    @Override
    public HttpRequest build(String target) {
        return HttpRequest.newBuilder()
                .uri(URI.create(target))
                .header(X_APP_LANGUAGE, RU_UA)
                .GET()
                .build();
    }
}
