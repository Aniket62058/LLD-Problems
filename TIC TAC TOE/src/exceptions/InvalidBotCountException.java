package exceptions;

public class InvalidBotCountException extends RuntimeException{
    public InvalidBotCountException(String message){
        super(message);
    }
}
