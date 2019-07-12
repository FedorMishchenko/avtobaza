package controller;

import constants.Path;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/managerTask")
public class ManagerTaskServlet extends HttpServlet {
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

        if(request.getParameter("approveUserId") != null &&
                request.getParameter("approveOrderId") != null) {
            System.out.println(request.getParameter("approveUserId"));
            System.out.println(request.getParameter("approveUserId"));
            System.out.println(request.getParameter("approveOrderId"));
            OrderService.approveOrder(request.getParameter("approveUserId"),
                    request.getParameter("approveOrderId"));
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

}
