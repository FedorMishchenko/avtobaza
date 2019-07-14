package controller.filter;

import config.request.UserRoleRequestWrapper;
import model.entity.UserAccount;
import utils.AppUtils;
import utils.SecurityUtils;
import utils.constants.Path;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.AppUtils.getLoginedUser;

/**
 * Filter checks access rights to web pages
 */
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

        /* User info stored in session
         *  (after successful login).
         */
        UserAccount loginedUser = getLoginedUser(request.getSession());

        if (servletPath.equals("/login" ) || servletPath.equals("/registration")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            String userName = loginedUser.getUserName();
            String role = loginedUser.getRole();

         /*
          *  Old request packet using new request with information userName and role.
          */
            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }

        /*
         * Pages requiring login.
         */
        if (SecurityUtils.isSecurityPage(request)) {

            /* If the user is not logged in yet,
            * redirect to login page.
            */
            if (loginedUser == null) {

                String requestUri = request.getRequestURI();

                /* Save the current page for redirection after successful login.*/
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
