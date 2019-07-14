package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import service.OrderService;
import utils.constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/managerTask")
public class ManagerTaskServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 2380446956990990962L;

    public ManagerTaskServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.MANAGER_PAGE);

        request.getSession().setAttribute("errorMassage", "");
        if(request.getParameter("approveUserId") != null &&
                request.getParameter("approveOrderId") != null) {
            try {
                OrderService.approveOrder(request.getParameter("approveUserId"),
                        request.getParameter("approveOrderId"));
            } catch (DaoException e) {
                GlobalExceptionHandler.handleException(e, request);
            }
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

}
