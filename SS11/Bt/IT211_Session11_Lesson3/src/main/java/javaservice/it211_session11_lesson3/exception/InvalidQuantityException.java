package javaservice.it211_session11_lesson3.exception;

public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(String message) {
        super(message);
    }
}