package service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper<T> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final Class<T> type;

    public Mapper(Class<T> type) {
        this.type = type;
    }

    public T parse(String response) {
        T result;
        try {
            result = mapper.readValue(response, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while converting = " + response, e);
        }

        return result;
    }
}
