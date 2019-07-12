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

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = -3118312440665956324L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> list = OrderService.findAllOpen();
        request.getSession().setAttribute("list", list);
        request.getRequestDispatcher(Path.LIST_ORDERS).forward(request, response);

    }
}
