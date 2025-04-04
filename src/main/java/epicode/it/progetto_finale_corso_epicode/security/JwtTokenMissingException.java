package epicode.it.progetto_finale_corso_epicode.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtTokenMissingException extends RuntimeException {

    public JwtTokenMissingException(String message) {

        super(message);
        logException(message);
    }

    public JwtTokenMissingException(String message, Throwable cause) {

        super(message, cause);
        logException(message, cause);
    }

    public JwtTokenMissingException(Throwable cause) {

        super(cause);
        logException(cause);
    }

    public JwtTokenMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
        logException(message, cause);
    }

    public JwtTokenMissingException() {

        super("JWT token is missing.");
        logException("JWT token is missing.");
    }

    private void logException(String message) {

        System.out.println("JwtTokenMissingException: " + message);
    }

    private void logException(String message, Throwable cause) {

        System.out.println("JwtTokenMissingException: " + message + ". Cause: " + cause);
    }

    private void logException(Throwable cause) {

        System.out.println("JwtTokenMissingException caused by: " + cause);
    }
}