package by.v.ch.exceptions;

public class CommandNotFoundException extends RuntimeException {

    private String message;
    private String command;

    public CommandNotFoundException(String command) {
        this.message = "Command "+command+" not found";
    }

}
