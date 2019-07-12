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

import static utils.AppUtils.getLoginedUser;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = -4795113449650215199L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserAccount loginedUser = getLoginedUser(request.getSession());

        String role = "user";
        if (loginedUser != null) {
            if (loginedUser.getRole().equals("admin")) {
                role = request.getParameter("role").trim();
                RequestDispatcher dispatcher
                        = this.getServletContext().getRequestDispatcher(Path.ADMIN_PAGE);
                dispatcher.forward(request, response);
            }
        }
        UserAccount userAccount = UserService.create(
                request.getParameter("userName").trim(),
                request.getParameter("password").trim(),
                role,
                request.getParameter("phone").trim(),
                request.getParameter("email").trim());


        if (userAccount == null) {
            String errorMessage = "Invalid userName or Password";

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher(Path.REGISTRATION);

            dispatcher.forward(request, response);
            return;
        }
        if(loginedUser == null){
            SecurityUtils.redirect(request, response, userAccount);
        }else {
            SecurityUtils.redirect(request, response, loginedUser);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.REGISTRATION);

        dispatcher.forward(request, response);

    }
}
