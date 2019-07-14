package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import entity.Order;
import service.OrderService;
import utils.constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/byUserID")
public class SortedByUserIDServlet extends HttpServlet {
    private static final long serialVersionUID = -6729804432438903132L;

    public SortedByUserIDServlet() { super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> list = new ArrayList<>();
        request.getSession().setAttribute("errorMassage", "");
        try {
            list = OrderService.findAllSortedByUserID();
        } catch (DaoException e) {
            GlobalExceptionHandler.handleException(e, request);
        }
        request.getSession().setAttribute("list", list);
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.LIST_ORDERS);
        dispatcher.forward(request, response);

    }
}
