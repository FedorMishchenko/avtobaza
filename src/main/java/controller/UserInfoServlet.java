package controller;

import constants.Path;
import model.entity.UserInfo;
import service.UserInfoService;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("userIdInfo") != null) {
            Integer id = Integer.valueOf(request.getParameter("userIdInfo"));
            UserInfo info = UserInfoService.findInfoByID(id);
            request.setAttribute("infoForm", info);
        }

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.INFO_GET);

        dispatcher.forward(request, response);
    }
}
