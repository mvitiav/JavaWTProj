package by.v.ch.controller;

import by.v.ch.command.Command;
import by.v.ch.command.CommandFactory;
import by.v.ch.command.CommandFactoryImpl;
import by.v.ch.dao.connection.ConnectionFactory;
import by.v.ch.exceptions.CommandExecutionException;
import by.v.ch.exceptions.CommandNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    final static String packageName = "/webAutoBase/";

    private static CommandFactory commandFactory;
    //private static Logger logger= LoggerFactory.getLogger(Controller.class);
    Logger logger;

    public Controller() {
        commandFactory = CommandFactoryImpl.getInstance();
        logger = LoggerFactory.getLogger(Controller.class);
        logger.info("Logger created");
        ConnectionFactory.getInstance().init();
    }

    private static String getClearUri(HttpServletRequest request) {
        String ret = request.getRequestURI();
        ret = ret.substring(request.getContextPath().length());
        return ret;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String path = "/";
        logger.info(getClearUri(request));
        if (0 != getClearUri(request).compareTo("/")) {
            try {
                //todo: log any request

                //todo check empty request
              String cmdName=  request.getParameter("button");


                logger.info("getClearUri(request)");
                Command command = commandFactory.getCommand(cmdName);

                logger.info(command.toString());


                path = command.execute(request);

            } catch (CommandNotFoundException | CommandExecutionException e) {
                logger.error(e.getMessage(),e);
                path = "/err";
                request.setAttribute("exception", e);
            } finally {
                forward(path, request, response);
            }
        }else {
            forward("/main", request, response);
        }
    }

    public void forward(String to, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("contextPath",request.getContextPath());
        logger.info(request.getContextPath());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(to);
        try {
            dispatcher.forward(request, response);

        } catch (ServletException | IOException e) { //todo:make own type of exception
            logger.error(e.getMessage(),e);
            //todo:drop to logger
        }
    }
}
