package config;

import java.util.*;

import static constants.Path.*;

/**
 * Security access web configurations
 */
public class SecurityConfig {

    public static final String ROLE_MANAGER = "manager";
    public static final String ROLE_USER = "user";
    public static final String ROLE_ADMIN = "admin";

    /**
     * String Roles
     * List urlPatterns valid for each Role
     */
    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    /**
     * init container with Roles configuration
     */
    private static void init() {
        mapConfig.put(ROLE_USER, USER_PATTERNS);

        mapConfig.put(ROLE_MANAGER, MANAGER_PATTERNS);

        mapConfig.put(ROLE_ADMIN, ADMIN_PATTERNS);
    }

    /**
     *
     * @return all available Roles
     */
    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    /**
     *
     * @param role
     * @return urlPatterns valid for this role
     */
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
