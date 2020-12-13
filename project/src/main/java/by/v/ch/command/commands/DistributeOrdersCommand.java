package by.v.ch.command.commands;

import by.v.ch.bean.Order;
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

public class DistributeOrdersCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(DistributeOrdersCommand.class);
    private final  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String NEXT_PAGE_URI = "WEB-INF/jsp/test.jsp";
    private static final String FINISH_TEST_PAGE_URI = "WEB-INF/jsp/finishTest.jsp";

    @Override
    public String execute(HttpServletRequest request) throws CommandExecutionException {
        logger.info("executed");
        User user=null;
        if(request.getSession().getAttribute("logged_USER")!=null){
            user= (User) request.getSession().getAttribute("logged_USER");}
        logger.info("DistributeOrdersCommand called from "+request.getSession().getAttribute("logged_USER"));
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String ret= "/pages/order.jsp";
        Object requestStatus=request.getParameter("distribute_status");

        if(requestStatus!=null){
            logger.info("requestStatus = "+(String)requestStatus);

            if((0==((String) requestStatus).compareTo("selected"))&&
                    (null!=request.getParameter("distribute_order"))){
             int orderId=Integer.parseInt(request.getParameter("distribute_order"));
             logger.info("order id ="+orderId);
            Order order=serviceFactory.getOrderService().getById(orderId);
            if(order!=null)
                logger.info(order.toString());
             request.setAttribute("distribute_order_instance",order);
                request.setAttribute("distribute_status","review");

            }
//            if(0==((String) requestStatus).compareTo("selected")){
//
//
//
//            }


        }else {
                if (user.getRole() == User.Role.dispatcher) {
                    logger.info("getting lastOrdersList");
                    request.setAttribute("distribute_status","list");
                    Order[] orders = serviceFactory.getOrderService().getUnsetOrders();
                    logger.info("lastOrderList length = " + orders.length);
                    request.setAttribute("distribute_list", orders);
                }
        }

        return ret;
    }
}
