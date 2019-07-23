package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import entity.Order;
import service.OrderService;
import constants.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet performs requests to display
 * all orders where status is open.
 * Requests can coming from userPage.jsp, managerPage.jsp, adminPage.jsp
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = -3118312440665956324L;

    public OrderServlet(){ super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().setAttribute("errorMassage", "");
        List<Order> list = new ArrayList<>();
        try {
            list = OrderService.findAllOpen();
        } catch (DaoException e) {
            GlobalExceptionHandler.handleException(e, request);
        }
        request.getSession().setAttribute("list", list);
        request.getRequestDispatcher(Path.LIST_ORDERS).forward(request, response);

    }
}
