package by.v.ch.command.commands;

import by.v.ch.bean.CarPurpose;
import by.v.ch.bean.User;
import by.v.ch.command.Command;
import by.v.ch.exceptions.CommandExecutionException;
import by.v.ch.services.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(RegisterCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandExecutionException {
        logger.info("register command execution started");
        Object request_name=request.getParameter("register_name");
        Object request_pass=request.getParameter("register_password");
        Object request_role=request.getParameter("register_role");
//        if(request_name==null){
//            logger.info("name is null");
//        }else {
//            logger.info("name is "+request_name);
//        }
//        if(request_pass==null){
//            logger.info("pass is null");
//        }else {
//            logger.info("pass is "+request_pass);
//        }
        //todo check also other fields
        if((request_name!=null)&&(request_pass!=null)){
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            logger.info("got instance");


            //todo: change to remove if
            //todo: make return of status more accurately
     if( serviceFactory.getUserService().register((String)request_name,(String)request_pass, User.Role.values()[Integer.parseInt((String) request_role)])){
         logger.info("Success");
         request.setAttribute("register_status","success");
     }else{
         logger.info("fail");
         request.setAttribute("register_status","fail");
     }
        }else {
            logger.info("new");
            request.setAttribute("register_status","new");
        }

        //todo: add logger
        return "/pages/register.jsp";
    }
}
