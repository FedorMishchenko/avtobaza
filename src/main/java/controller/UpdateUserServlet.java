package controller;

import constants.Path;
import model.entity.UserAccount;
import service.UserService;
import utils.SecurityUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = -207562576336109450L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserAccount current = (UserAccount) request.getSession().getAttribute("loginedUser");
        UserAccount userAccount = UserService.update(
                current.getId(),
                request.getParameter("userName"),
                request.getParameter("password"),
                request.getParameter("phone"),
                request.getParameter("email"));

        SecurityUtils.redirect(request,response,userAccount);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.UPDATE_USER);

        dispatcher.forward(request, response);

    }
}
