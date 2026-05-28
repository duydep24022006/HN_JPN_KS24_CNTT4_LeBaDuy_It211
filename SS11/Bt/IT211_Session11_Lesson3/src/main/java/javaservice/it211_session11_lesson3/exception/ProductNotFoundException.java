package javaservice.it211_session11_lesson3.exception;

    public class ProductNotFoundException extends RuntimeException {

        public ProductNotFoundException(String message) {
            super(message);
        }
    }