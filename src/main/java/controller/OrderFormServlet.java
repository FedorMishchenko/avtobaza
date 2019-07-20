package controller;

import exceptions.DaoException;
import exceptions.ValidationException;
import service.OrderService;
import constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

import static constants.Massages.*;

@WebServlet("/orderForm")
public class OrderFormServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = -2584908385925829836L;

    public OrderFormServlet() { super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("massage", "input data");
        if (request.getParameter("startPoint") != null) {

            try {
                OrderService.create(
                        request.getParameter("startPoint").trim(),
                        request.getParameter("destination").trim(),
                        request.getParameter("distance").trim(),
                        request.getParameter("status").trim()
                );
                request.getSession().setAttribute("massage", ORDER_FORM_MASSAGE);
            } catch (ValidationException | DaoException e) {
                if (e.getClass().getSimpleName().equals("DaoException")) {
                    request.getSession().setAttribute("massage", ERROR_MASSAGE);
                } else {
                    request.getSession().setAttribute("massage", ERROR_VALIDATION);
                }
                return;
            }
        }
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.ORDER_FORM);

        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }
}
