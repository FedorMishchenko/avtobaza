package controller;

import constants.Path;
import model.entity.UserAccount;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listUsers")
public class ListUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 5752405503434618163L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserAccount> listUsers = UserService.findAll();
        request.getSession().setAttribute("listUsers", listUsers);
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.LIST_USERS);
        dispatcher.forward(request, response);

    }
}
