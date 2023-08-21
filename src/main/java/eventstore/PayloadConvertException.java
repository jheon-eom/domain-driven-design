package eventstore;

import com.fasterxml.jackson.core.JsonProcessingException;

public class PayloadConvertException extends RuntimeException {

    public PayloadConvertException(JsonProcessingException e) {

    }
}
