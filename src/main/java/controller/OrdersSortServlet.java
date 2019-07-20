package controller;

import entity.Order;
import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import service.OrderService;
import constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orderSort")
public class OrdersSortServlet extends HttpServlet {
    private static final long serialVersionUID = -5089303457442592467L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> list = new ArrayList<>();
        request.getSession().setAttribute("errorMassage", "");

        Integer id = Integer.valueOf(request.getParameter("number"));
        try {
            switch (id) {
                case 1:
                    list = OrderService.findAllSortedByDate();
                    break;
                case 2:
                    list = OrderService.findAllSortedByID();
                    break;
                case 3:
                    list = OrderService.findAllSortedByDistance();
                    break;
                case 4:
                    list = OrderService.findAllSortedByUserID();
                    break;
                default:
                    break;
            }
        }catch (DaoException e) {
            GlobalExceptionHandler.handleException(e, request);
        }

        request.getSession().setAttribute("list", list);
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.LIST_ORDERS);

        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
