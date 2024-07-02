package pfc.cebem.back.exception;

public class NotEnoughSeatsException extends RuntimeException {

    public NotEnoughSeatsException(String message) {
        super(message);
    }
}
