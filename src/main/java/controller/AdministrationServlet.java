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

@WebServlet("/admin")
public class AdministrationServlet extends HttpServlet {
    private static final long serialVersionUID = -7018663483968986116L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserAccount> listUsers = UserService.findAllAdministration();
        request.getSession().setAttribute("listUsers", listUsers);
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.LIST_USERS);
        dispatcher.forward(request, response);

    }
}
