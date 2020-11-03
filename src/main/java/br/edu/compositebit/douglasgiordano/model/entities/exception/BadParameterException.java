package br.edu.compositebit.douglasgiordano.model.entities.exception;

public class BadParameterException extends Exception {
    public BadParameterException() {
        super("No entities found.");
    }

    public BadParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadParameterException(String message) {
        super(message);
    }
}
