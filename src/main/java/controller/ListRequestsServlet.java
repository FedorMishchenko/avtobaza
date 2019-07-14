package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import model.entity.Request;
import service.UserService;
import utils.constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@WebServlet("/listRequests")
public class ListRequestsServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = -3583929740574903484L;

    public ListRequestsServlet(){ super(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute("errorMassage", "");
        List<Request> listRequests = null;
        try {
            listRequests = UserService.findRequests();
        } catch (DaoException e) {
            GlobalExceptionHandler.handleException(e, request);
        }

        request.getSession().setAttribute("listRequests", listRequests);
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.LIST_REQUESTS);
        dispatcher.forward(request, response);

    }
}
