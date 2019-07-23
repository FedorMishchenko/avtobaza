package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import exceptions.ValidationException;
import entity.UserAccount;
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

import static utils.AppUtils.getLoginedUser;

/**
 * Servlet performs requests to create userInfo,
 * coming from the infoForm.jsp
 */
@WebServlet("/infoForm")
public class InfoFormServlet extends HttpServlet {
    private static final long serialVersionUID = -3742507496137199119L;

    public InfoFormServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().setAttribute("errorMassage", "");
        UserAccount account = getLoginedUser(request.getSession());
        if (request.getParameter("truck") != null && request.getParameter("tr_status") != null &&
                request.getParameter("capacity") != null) {
            UserInfo info = new UserInfo();
            try {
                info = UserInfoService.create(
                        request.getParameter("truck"),
                        request.getParameter("tr_status"),
                        request.getParameter("capacity"),
                        account.getId()
                );
            }catch (DaoException | ValidationException e) {
                GlobalExceptionHandler.handleException(e, request);
            }
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