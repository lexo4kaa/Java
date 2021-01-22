package root.exception;

public class ArrayException extends Exception {
    public ArrayException() {

    }

    public ArrayException(String message) {
        super(message);
    }

    public ArrayException(Throwable cause) {
        super(cause);
    }

    public ArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
