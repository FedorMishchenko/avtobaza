package utils;

import config.SecurityConfig;
import entity.UserAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static utils.AppUtils.storeLoginedUser;


public final class SecurityUtils {

    /**
     * Checks if this login request is required or not
     *
     * @param request HttpServletRequest
     * @return depending on the condition true or false
     */
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);

        Set<String> roles = SecurityConfig.getAllAppRoles();

        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method checks if the given request has a suitable role.
     *
     * @param request HttpServletRequest
     * @return depending on the condition true or false
     */
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);

        Set<String> allRoles = SecurityConfig.getAllAppRoles();

        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method redirects after logging in to an accessible userInfo page
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param userAccount UserAccount
     * @throws IOException
     */
    public static void redirect(HttpServletRequest request, HttpServletResponse response,
                                    UserAccount userAccount) throws IOException {
        storeLoginedUser(request.getSession(), userAccount);

        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception ignored) {
            /*NOP*/
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(/*request.getSession(),*/ redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        } else {
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
}
