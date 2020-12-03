package by.v.ch.controller;

import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response){
    


    forward("/main",request,response);
    }
    public void forward(String to, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(to);
        try {
            dispatcher.forward(request, response);

        } catch (ServletException | IOException e) { //todo:make own type of exception
            e.printStackTrace();
            //todo:drop to logger
        }
    }
}
