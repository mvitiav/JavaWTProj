package by.v.ch.command.commands;

import by.v.ch.bean.User;
import by.v.ch.command.Command;
import by.v.ch.controller.Controller;
import by.v.ch.exceptions.CommandExecutionException;
import by.v.ch.services.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LogInCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(LogInCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandExecutionException {
        String ret="/pages/login.jsp";
        logger.info("login request!");
        Object request_name=request.getParameter("login_name");
        Object request_pass=request.getParameter("login_password");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
//        String login=request.getParameter("login_name");
//        String password=request.getParameter("login_password");

        if((request_name!=null)&&(request_pass!=null)){


            logger.info("login : "+request_name+" pass: "+request_pass);

            User user= serviceFactory.getUserService().logIn((String)request_name,(String) request_pass);

            if(user!=null){
                request.setAttribute("login_status","success");
                request.setAttribute("logged_USER",user);
                ret="/pages/index.jsp";
            }else {
                request.setAttribute("login_status","fail");
            }

        }else {
            request.setAttribute("login_status","new");
        }


       //todo: check who is user
//       if(user.getClass().i)


        return ret;
    }
}
