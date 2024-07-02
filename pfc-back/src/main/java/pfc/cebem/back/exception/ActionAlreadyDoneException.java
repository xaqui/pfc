package pfc.cebem.back.exception;

public class ActionAlreadyDoneException extends RuntimeException {
    
    public ActionAlreadyDoneException(String message) {
        super(message);
    }

}
