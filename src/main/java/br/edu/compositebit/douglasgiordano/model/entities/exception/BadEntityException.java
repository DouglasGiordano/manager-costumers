package br.edu.compositebit.douglasgiordano.model.entities.exception;

public class BadEntityException extends Exception {
    public BadEntityException() {
        super("No entities found.");
    }

    public BadEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadEntityException(String message) {
        super(message);
    }
}
