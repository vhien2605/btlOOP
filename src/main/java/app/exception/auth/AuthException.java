package app.exception.auth;

public abstract class AuthException extends Exception {
    public AuthException(String message) {
        super(message);
    }
}
