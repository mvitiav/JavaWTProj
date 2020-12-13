package by.v.ch.command.commands;

import by.v.ch.command.Command;
import by.v.ch.exceptions.CommandExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(DistributeOrdersCommand.class);

    private final static Locale locale1=new Locale("ru","RU");
    private final static Locale locale2=new Locale("en","US");

    @Override
    public String execute(HttpServletRequest request) throws CommandExecutionException {
        logger.info("lang_cmd executed");

        if(request.getSession().getAttribute("locale")==locale1){
            logger.info("if");
            request.getSession().setAttribute("locale",locale2);
        }else {
            logger.info("else");
            request.getSession().setAttribute("locale",locale1);
        }

        return "/pages/index.jsp";
    }
}
