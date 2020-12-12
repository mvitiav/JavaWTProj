package by.v.ch.command.commands;

import by.v.ch.command.Command;
import by.v.ch.exceptions.CommandExecutionException;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandExecutionException {
        return null;
    }
}
