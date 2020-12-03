package by.v.ch.exceptions;

import by.v.ch.command.Command;

public class CommandExecutionException extends RuntimeException{

    private String message;

    public CommandExecutionException(Command command,Throwable cause) {
        super(cause);
        message = "Exception was thrown while executing "+command.getClass().toString();
    }

}
