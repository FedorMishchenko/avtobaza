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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -6690840938643609966L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.LOGIN);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName").trim();
        String password = request.getParameter("password").trim();
        UserAccount userAccount = UserService.findUser(userName, password);

        if (userAccount.getRole() == null) {
            String errorMessage = "Invalid userName or Password";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher(Path.LOGIN);

            dispatcher.forward(request, response);
            return;
        }
        SecurityUtils.redirect(request, response, userAccount);

    }

}
