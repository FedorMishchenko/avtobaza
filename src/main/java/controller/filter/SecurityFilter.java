package controller.filter;

import constants.Path;
import model.entity.UserAccount;
import config.request.UserRoleRequestWrapper;
import utils.AppUtils;
import utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.AppUtils.getLoginedUser;


@WebFilter("/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        UserAccount loginedUser = getLoginedUser(request.getSession());

        if (servletPath.equals("/login" ) || servletPath.equals("/registration")
        /*|| servletPath.equals("/order")*/) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            // User Name
            String userName = loginedUser.getUserName();

            // Роли (Role).
            String role = loginedUser.getRole();

            // Старый пакет config.request с помощью нового Request с информацией userName и Roles.
            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }

        // Страницы требующие входа в систему.
        if (SecurityUtils.isSecurityPage(request)) {

            // Если пользователь еще не вошел в систему,
            // Redirect (перенаправить) к странице логина.
            if (loginedUser == null) {

                String requestUri = request.getRequestURI();

                // Сохранить текущую страницу для перенаправления (redirect) после успешного входа в систему.
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            /*Check whether the user has a valid role or not*/
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher
                        = request.getServletContext().getRequestDispatcher(Path.ACCESS_DENIED);

                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) {

    }

}
