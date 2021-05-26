package fr.alexandresarouille.controllers;

import fr.alexandresarouille.errors.ApiError;
import fr.alexandresarouille.exceptions.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotExistException(@NotNull EntityNotExistException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleEntityExistException(@NotNull EntityExistException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BookNoQuantityException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected ResponseEntity<Object> handleBookNoQuantityException(@NotNull BookNoQuantityException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(LoanAlreadyExtendedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected ResponseEntity<Object> handleLoanAlreadyExtendedException(@NotNull LoanAlreadyExtendedException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(SameBookLoanForUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleSameBookLoanForUserException(@NotNull SameBookLoanForUserException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleUsernameNotFoundException(@NotNull UsernameNotFoundException ex){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(@NotNull ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
