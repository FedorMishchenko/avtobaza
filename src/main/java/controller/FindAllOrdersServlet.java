package controller;

import constants.Path;
import model.entity.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allOrders")
public class FindAllOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = -2291561463551887154L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> list = OrderService.findAll();
        request.getSession().setAttribute("list", list);
        request.getRequestDispatcher(Path.LIST_ORDERS).forward(request, response);

    }
}
