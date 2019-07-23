package config.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

/**
 * An extension for the HTTPServletRequest that overrides the getUserPrincipal()
 * and isUserInRole(). We supply these implementations here, where they are not
 * normally populated unless we are going through the facility provided by the
 * container.
 * <p>
 * If the user or roles are null on this wrapper, the parent config.request is consulted
 * to try to fetch what ever the container has set for it. This is intended to
 * be created and used by the UserRoleFilter.
 *
 * @author F.Mishchenko
 */
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private String user;
    private List<String> roles;
    private HttpServletRequest realRequest;

    public UserRoleRequestWrapper(String user, String role, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.roles = Collections.singletonList(role);
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (roles == null) {
            return this.realRequest.isUserInRole(role);
        }
        return roles.contains(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }
        return () -> user;
    }
}
