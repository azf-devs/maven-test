package exceptions;

public class InvalidEntityException extends BusinessException {

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException() {
        super("Invalid entity");
    }
}
