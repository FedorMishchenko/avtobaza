package controller;


import config.SecurityConfig;
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
import java.io.Serializable;

import static utils.AppUtils.getLoginedUser;
import static constants.Massages.ERROR_MASSAGE;

/**
 * Servlet performs requests to register new user,
 * coming from registrationForm.jsp
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = -4795113449650215199L;

    public RegistrationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserAccount loginedUser = getLoginedUser(request.getSession());

        request.getSession().setAttribute("errorMassage", "");
        String role = SecurityConfig.ROLE_USER;
        if (loginedUser != null) {
            if (loginedUser.getRole().equals(SecurityConfig.ROLE_ADMIN)) {
                role = request.getParameter("role").trim();
                RequestDispatcher dispatcher
                        = this.getServletContext().getRequestDispatcher(Path.ADMIN_PAGE);
                dispatcher.forward(request, response);
            }
        }
        UserAccount userAccount = null;
        try {
            userAccount = UserService.create(
                    request.getParameter("userName").trim(),
                    request.getParameter("password").trim(),
                    role,
                    request.getParameter("phone").trim(),
                    request.getParameter("email").trim());
        } catch (DaoException | ValidationException e) {
            GlobalExceptionHandler.handleException(e, request);
        }

        if (userAccount == null) {

            request.setAttribute("errorMessage", ERROR_MASSAGE);

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher(Path.REGISTRATION);

            dispatcher.forward(request, response);
            return;
        }
        SecurityUtils.redirect(request, response, userAccount);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.REGISTRATION);

        dispatcher.forward(request, response);

    }
}
