package javaservice.it211_session11_lesson3.exception;
public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String message) {
        super(message);
    }
}