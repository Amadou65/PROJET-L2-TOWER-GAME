package game.exeptions;

public class TooLongPathException extends Exception {
    public TooLongPathException(String message) {
        super(message);
    }
}
