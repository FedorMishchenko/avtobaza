package controller;

import constants.Path;
import model.entity.UserAccount;
import model.entity.UserInfo;
import service.UserInfoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.AppUtils.getLoginedUser;

@WebServlet("/infoForm")
public class InfoFormServlet extends HttpServlet {
    private static final long serialVersionUID = -3742507496137199119L;

    public InfoFormServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserAccount account = getLoginedUser(request.getSession());
        if (request.getParameter("truck") != null && request.getParameter("tr_status") != null &&
                request.getParameter("capacity") != null) {
            UserInfo info = UserInfoService.create(
                    request.getParameter("truck"),
                    request.getParameter("tr_status"),
                    request.getParameter("capacity"),
                    account.getId()
            );
            info.setUserID(account.getId());
            request.setAttribute("infoForm", info);
        }


        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.INFO_FORM);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}