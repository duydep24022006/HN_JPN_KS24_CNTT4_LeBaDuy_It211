package javaservice.it211_session11_lesson3.exception;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(String message) {
        super(message);
    }
}