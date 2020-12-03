package by.v.ch.command;

import by.v.ch.exceptions.CommandNotFoundException;

public interface CommandFactory {
    public CommandFactory getInstance();
    public Command getCommand(String commandName) throws CommandNotFoundException;
}
