package by.v.ch.command;

import by.v.ch.exceptions.CommandExecutionException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    public String execute(HttpServletRequest request) throws CommandExecutionException;

}
