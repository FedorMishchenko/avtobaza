package controller;

import constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet performs requests to display userAccount information,
 * coming from index.jsp
 */
@WebServlet("/userInfo")
public class UserAccountInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 5076643494648454287L;

    public UserAccountInfoServlet() { super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.USER_INFO);

        dispatcher.forward(request, response);

    }
}
