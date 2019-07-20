package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import entity.UserInfo;
import service.UserInfoService;
import constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/info")
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = -2903106038889505714L;

    public UserInfoServlet(){ super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().setAttribute("errorMassage", "");
        if(request.getParameter("userIdInfo") != null) {
            Integer id = Integer.valueOf(request.getParameter("userIdInfo"));
            UserInfo info = null;
            try {
                info = UserInfoService.findInfoByID(id);
            } catch (DaoException e) {
                GlobalExceptionHandler.handleException(e, request);
            }
            request.setAttribute("infoForm", info);

        }


        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.INFO_GET);

        dispatcher.forward(request, response);
    }
}
