package by.v.ch.command.commands;

import by.v.ch.bean.User;
import by.v.ch.command.Command;
import by.v.ch.exceptions.CommandExecutionException;
import by.v.ch.services.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeOrderCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(MakeOrderCommand.class);

    private final  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final String NEXT_PAGE_URI = "WEB-INF/jsp/test.jsp";
    private static final String FINISH_TEST_PAGE_URI = "WEB-INF/jsp/finishTest.jsp";

    @Override
    public String execute(HttpServletRequest request) throws CommandExecutionException {
logger.info("placeOrderCommand called from "+request.getSession().getAttribute("logged_USER"));
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

String ret= "/pages/order.jsp";
Object requestStatus=request.getParameter("order_status");

if(requestStatus!=null){

    logger.info("requestStatus = "+(String)requestStatus);
    if(0==((String) requestStatus).compareTo("filled")){

        //addOrder(User user, float size, float volume, float weight, Date shipmentDate, Date destinationDate, String shipmentpoint, String destinationpoint,int purposeId);

        logger.info("filled:");

        User user= (User) request.getSession().getAttribute("logged_USER");
        logger.info("user = "+user);
        float size= Float.parseFloat(request.getParameter("order_size"));
        logger.info("size = "+size);
        float volume=Float.parseFloat(request.getParameter("order_volume"));
        logger.info("volume = "+volume);
        float weight= Float.parseFloat(request.getParameter("order_weight"));
        logger.info("weight = "+weight);
        Date shipmentDate= null;
        Date destinationDate= null;
        try {
            shipmentDate = sdf.parse( request.getParameter("order_shipmentDate"));
        logger.info("shipmentDate = "+shipmentDate);
        destinationDate= sdf.parse(request.getParameter("order_destinationDate"));
        logger.info("destinationDate = "+destinationDate);
        } catch (ParseException e) {
         //todo: fixme!!
            e.printStackTrace();
        }
                String shipmentpoint=request.getParameter("order_shipmentPoint");
        logger.info("shipmentpoint = "+shipmentpoint);
                String destinationpoint=request.getParameter("order_destinationPoint");
        logger.info("destinationpoint = "+destinationpoint);
                logger.info("addingOrder");

        serviceFactory.getOrderService().addOrder(
                user,size,volume,weight,
                shipmentDate,
                destinationDate,
                shipmentpoint,
                destinationpoint,
                1
        );


    }

}


        return ret;
    }
}
