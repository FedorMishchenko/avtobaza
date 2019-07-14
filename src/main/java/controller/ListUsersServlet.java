package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import entity.UserAccount;
import service.UserService;
import utils.constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listUsers")
public class ListUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 5752405503434618163L;

    public ListUsersServlet(){ super();}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserAccount> listUsers = new ArrayList<>();
        request.getSession().setAttribute("errorMassage", "");
        try {
            listUsers = UserService.findAll();
        } catch (DaoException e) {
            GlobalExceptionHandler.handleException(e, request);
        }
        request.getSession().setAttribute("listUsers", listUsers);
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher(Path.LIST_USERS);
        dispatcher.forward(request, response);

    }
}
