package app.exception.auth;

public class DuplicateException extends AuthException {
    public DuplicateException(String message) {
        super(message);
    }
}
