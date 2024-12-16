package edu.school21.backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionUtil {

    public static ResponseStatusException createNotFoundException(final String message) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }

    public static ResponseStatusException createBadRequestException(final String message) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }
}
