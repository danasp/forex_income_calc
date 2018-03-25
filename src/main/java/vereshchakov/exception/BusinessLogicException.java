package vereshchakov.exception;

/**
 * Created by Danila Vereshchakov on 24.03.18.
 */
public class BusinessLogicException extends RuntimeException {

    public BusinessLogicException() {
    }

    public BusinessLogicException(String message) {
        super(message);
    }
}
