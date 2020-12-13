package by.v.ch.command.commands;

import by.v.ch.bean.CarPurpose;
import by.v.ch.command.Command;
import by.v.ch.exceptions.CommandExecutionException;
import by.v.ch.services.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ShowInfoCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(ShowInfoCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandExecutionException {
        logger.info("showinfo request!");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        logger.info("got instance");
        CarPurpose purpose= serviceFactory.getTruckService().getCarPurposeById(1);
        CarPurpose[] purposes= serviceFactory.getTruckService().getAllCarPurposes();
        logger.info("got purpose");
        request.setAttribute("carPurpose1",purpose);
        request.setAttribute("carPurposes",purposes);
        logger.info("returning");
        return "/pages/about.jsp";
        //return null;
    }
}
