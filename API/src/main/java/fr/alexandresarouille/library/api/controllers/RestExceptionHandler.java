package fr.alexandresarouille.library.api.controllers;

import fr.alexandresarouille.library.api.exceptions.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            EntityNotExistException.class,
            EntityExistException.class,
            BookNoQuantityException.class,
            LoanAlreadyExtendedException.class,
            SameBookLoanForUserException.class,
    })
    protected ResponseEntity<Object> handleRestException(@NotNull CustomRestException ex) {
        return ResponseEntity.status(ex.getStatueCodeValue()).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFoundException(@NotNull UsernameNotFoundException ex) {
        return ResponseEntity.status(460).body(ex.getMessage());
    }
}
