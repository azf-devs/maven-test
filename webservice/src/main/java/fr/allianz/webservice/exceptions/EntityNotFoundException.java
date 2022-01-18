package fr.allianz.webservice.exceptions;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException() {
        this("Entity not found");
    }
}
