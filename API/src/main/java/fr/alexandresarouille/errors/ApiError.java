package fr.alexandresarouille.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ApiError {

    /**
     * Http error stat
     * format -> 4**
     */
    private HttpStatus status;

    /**
     *  Local date of when the error occured
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss")
    private LocalDateTime localDateTime;

    /**
     * The error's message send to the client
     */
    private String message;

    /**
     * The error's message with trace to debug
     */
    private String debugMessage;

    public ApiError() {
        localDateTime = LocalDateTime.now();
    }

    public ApiError(@NotNull HttpStatus status) {
        this();
        this.status=status;
    }

    public ApiError(@NotNull HttpStatus status,@NotNull Throwable throwable) {
        this(status);
        this.message = "Erreur inatendue";
        this.debugMessage = throwable.getLocalizedMessage();
    }

    public ApiError(@NotNull HttpStatus status,@NotNull String message,@NotNull Throwable throwable) {
        this(status, throwable);
        this.message=message;
    }

}
