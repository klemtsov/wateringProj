package ru.klemtsov.watering.restserver.exception;

public class WateringException extends Exception {

    private static final long serialVersionUID = 1L;

    public WateringException(String message) {
        super(message);
    }
}
