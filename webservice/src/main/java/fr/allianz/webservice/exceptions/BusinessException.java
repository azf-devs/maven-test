package fr.allianz.webservice.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
