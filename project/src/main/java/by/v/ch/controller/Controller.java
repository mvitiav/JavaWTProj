package by.v.ch.controller;

import by.v.ch.command.Command;
import by.v.ch.command.CommandFactory;
import by.v.ch.command.CommandFactoryImpl;
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
    public Controller() {
        commandFactory = CommandFactoryImpl.getInstance();
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
        //logger.info("[]"+getClearUri(request));
        if (0 != getClearUri(request).compareTo("/")) {
            try {
                //todo: log any request
                System.out.println("getClearUri(request)");
                Command command = commandFactory.getCommand(getClearUri(request));
                System.out.println(command);
                path = command.execute(request);

            } catch (CommandNotFoundException | CommandExecutionException e) {
                System.out.println(e.getMessage());
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
        System.out.println(request.getContextPath());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(to);
        try {
            dispatcher.forward(request, response);

        } catch (ServletException | IOException e) { //todo:make own type of exception
            e.printStackTrace();
            //todo:drop to logger
        }
    }
}
