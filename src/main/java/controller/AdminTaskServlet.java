package controller;

import constants.Path;
import service.OrderService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/adminTask")
public class AdminTaskServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 4271454643855224679L;

    public AdminTaskServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("userName") != null){
            UserService.create(
                    request.getParameter("userName"),
                    request.getParameter("password"),
                    request.getParameter("role"),
                    request.getParameter("phone"),
                    request.getParameter("email"));
        }
        if(request.getParameter("deleteUserId") != null){
            UserService.delete(request.getParameter("deleteUserId"));
        }
        if(request.getParameter("deleteOrderId") != null){
            OrderService.delete(request.getParameter("deleteOrderId"));
        }
        if(request.getParameter("setUserRole") != null &&
                request.getParameter("setUserId")!= null ){
            UserService.setRole(request.getParameter("setUserRole"),request.getParameter("setUserId"));
        }

        request.getServletContext()
                .getRequestDispatcher(Path.ADMIN_PAGE)
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
