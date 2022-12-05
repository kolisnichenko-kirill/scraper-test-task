package service.request;

import java.net.http.HttpRequest;

public interface HttpRequestBuilder {
    HttpRequest build(String target);
}
