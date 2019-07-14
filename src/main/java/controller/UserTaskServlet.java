package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import entity.Order;
import entity.UserAccount;
import service.OrderService;
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

@WebServlet("/userTask")
public class UserTaskServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 2078982781727188991L;

    public UserTaskServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        UserAccount u = (UserAccount) request.getSession().getAttribute("loginedUser");
        request.getSession().setAttribute("errorMassage", "");
        try {
            if (u != null) {

                if (request.getParameter("requestOrderId") != null) {
                    UserService.createRequest(u.getId(), request.getParameter("requestOrderId"));

                }
                Order o = OrderService.findOrder(u.getId());
                request.getSession().setAttribute("id", o.getId());
                request.getSession().setAttribute("startPoint", o.getStartPoint());
                request.getSession().setAttribute("destination", o.getDestination());
                request.getSession().setAttribute("distance", o.getDistance());
                request.getSession().setAttribute("status", o.getStatus());
                request.getSession().setAttribute("date", o.getDate());

                List<Order> listUserOrders = OrderService.findAll(u.getId());
                request.getSession().setAttribute("listUserOrders", listUserOrders);

                if (request.getParameter("closeOrder") != null) {
                    OrderService.closeOrder(request.getParameter("closeOrder"));
                }
            }
        } catch (DaoException e) {
            GlobalExceptionHandler.handleException(e, request);
        }

        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.USER_PAGE);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

}