package fr.alexandresarouille.controllers;

import fr.alexandresarouille.errors.ApiError;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHAndler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotExistException.class)
    protected ResponseEntity<Object> handleEntityNotExistException(@NotNull EntityNotExistException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityExistException.class)
    protected ResponseEntity<Object> handleEntityExistException(@NotNull EntityExistException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(@NotNull ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
