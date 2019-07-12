package controller;

import constants.Path;
import exceptions.ValidationException;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/orderForm")
public class OrderFormServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = -2584908385925829836L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("massage", "input data");
        if (request.getParameter("startPoint") != null) {

            try {
                OrderService.create(
                        request.getParameter("startPoint").trim(),
                        request.getParameter("destination").trim(),
                        request.getParameter("distance").trim(),
                        request.getParameter("status").trim(),
                        /*config.request.getParameter("user_id").trim()*/"0"
                );
                request.setAttribute("massage", "order created successful");
            }catch (ValidationException e){
                request.setAttribute("massage", e.getMessage());
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
