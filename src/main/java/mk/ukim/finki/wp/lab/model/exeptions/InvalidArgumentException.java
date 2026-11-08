package mk.ukim.finki.wp.lab.model.exeptions;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException() {
        super("Please fill in all required fields.");
    }

    public InvalidArgumentException(String message) {
        super(message);
    }
}
