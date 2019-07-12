package controller;

import constants.Path;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({ "/", "/index" })
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = -6931129773575521453L;
    private static Logger LOGGER = Logger.getLogger(HomeServlet.class);

    public HomeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.trace("doGet");
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.HOME);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.trace("doPost-doGet");
        doGet(request, response);
    }

}
