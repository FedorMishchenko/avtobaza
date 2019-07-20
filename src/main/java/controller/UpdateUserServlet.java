package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import exceptions.ValidationException;
import entity.UserAccount;
import service.UserService;
import utils.SecurityUtils;
import constants.Path;

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

    public UpdateUserServlet() { super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UserAccount current = (UserAccount) request.getSession().getAttribute("loginedUser");
        UserAccount userAccount = new UserAccount();
        request.getSession().setAttribute("errorMassage", "");
        try {
            userAccount = UserService.update(
                    current.getId(),
                    request.getParameter("userName"),
                    request.getParameter("password"),
                    request.getParameter("phone"),
                    request.getParameter("email"));
        } catch (ValidationException | DaoException e) {
            GlobalExceptionHandler.handleException(e, request);
        }

        SecurityUtils.redirect(request,response,userAccount);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.UPDATE_USER);

        dispatcher.forward(request, response);

    }
}
