package br.edu.compositebit.douglasgiordano.model.entities.exception;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
        super("No entities found.");
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
