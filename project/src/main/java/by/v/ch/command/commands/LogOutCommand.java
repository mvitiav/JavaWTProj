package by.v.ch.command.commands;

import by.v.ch.bean.User;
import by.v.ch.command.Command;
import by.v.ch.exceptions.CommandExecutionException;
import by.v.ch.services.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(LogOutCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandExecutionException {
        String ret="/pages/index.jsp";
        logger.info("logOut request!");

        //todo make it with session?
        request.setAttribute("logged_USER",null);

        return ret;
    }
}
