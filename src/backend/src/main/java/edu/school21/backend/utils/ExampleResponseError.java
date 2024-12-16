package edu.school21.backend.utils;

/**
 * Класс генерации примеров ошибок для сваггера
 */
public final class ExampleResponseError {
    public static final String BAD_REQUEST = """
                {
                  "type": "about:blank",
                  "title": "Bad Request",
                  "status": 400,
                  "detail": "Failed to read request",
                  "instance": "/v1/supplier/add"
                }
                """;

    public static final String NOT_FOUND = """
                {
                  "type": "about:blank",
                  "title": "Not Found",
                  "status": 404,
                  "detail": "Not found client by id = 12",
                  "instance": "/v1/client/12/update-address"
                }
                """;

    public static final String INTERNAL_SERVER_ERROR = """
                {
                   "timestamp": "2024-09-25T11:57:32.896+00:00",
                   "status": 500,
                   "error": "Internal Server Error",
                   "path": "/v1/product/add"
                }
                """;

    private ExampleResponseError() {
    }

}
