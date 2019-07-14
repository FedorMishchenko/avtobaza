package controller;

import exceptions.DaoException;
import exceptions.GlobalExceptionHandler;
import exceptions.ValidationException;
import model.entity.UserAccount;
import model.entity.UserInfo;
import service.UserInfoService;
import utils.constants.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/infoUpdate")
public class InfoUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = -2120303855665413426L;

    public InfoUpdateServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().setAttribute("errorMassage", "");
        UserAccount account = (UserAccount) request.getSession().getAttribute("loginedUser");
        if (request.getParameter("truck") != null && request.getParameter("tr_status") != null &&
                request.getParameter("capacity") != null) {
            UserInfo info = new UserInfo();
            try {
                info = UserInfoService.update(
                        request.getParameter("truck"),
                        request.getParameter("tr_status"),
                        request.getParameter("capacity"),
                        account.getId()
                );
            } catch (DaoException | ValidationException e) {
                GlobalExceptionHandler.handleException(e, request);
            }
            info.setUserID(account.getId());
            request.setAttribute("infoForm", info);
        }
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Path.INFO_UPDATE);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
