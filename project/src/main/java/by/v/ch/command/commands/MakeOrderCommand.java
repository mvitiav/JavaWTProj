package by.v.ch.command.commands;

import by.v.ch.command.Command;
import by.v.ch.exceptions.CommandExecutionException;

import javax.servlet.http.HttpServletRequest;

public class MakeOrderCommand implements Command {
    private static final String NEXT_PAGE_URI = "WEB-INF/jsp/test.jsp";
    private static final String FINISH_TEST_PAGE_URI = "WEB-INF/jsp/finishTest.jsp";

    @Override
    public String execute(HttpServletRequest request) throws CommandExecutionException {


        return "/pages/order.jsp";
//        request.getRequestDispatcher(NEXT_PAGE_URI);
//        requestDispatcher.forward(req, resp);

       // return null;
    }
}
