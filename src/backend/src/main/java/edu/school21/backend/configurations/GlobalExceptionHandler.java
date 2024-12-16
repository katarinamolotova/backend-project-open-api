package edu.school21.backend.configurations;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Обработчик ошибок.
 * Нужен чтобы при выбросе ResponseStatusException выводился текст сообщения.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
}
