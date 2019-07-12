package controller;

import constants.Path;
import model.entity.Order;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/byID")
public class SortedOrdersByIDServlet extends HttpServlet {
    private static final long serialVersionUID = -4261734414574007077L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> list = OrderService.findAllSortedByID();
        request.getSession().setAttribute("list", list);
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.LIST_ORDERS);
        dispatcher.forward(request, response);

    }
}
