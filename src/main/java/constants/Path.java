package constants;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class constants used in commands to access the page
 */
public final class Path {

    public static final String USER_INFO = "/views/userAccountInfo.jsp";
    public static final String USER_PAGE = "/views/userPage.jsp";
    public static final String UPDATE_USER = "/views/updateUser.jsp";
    public static final String REGISTRATION = "/views/registration.jsp";
    public static final String LIST_ORDERS = "/views/listOrders.jsp";
    public static final String ORDER_FORM = "/views/orderForm.jsp";
    public static final String MANAGER_PAGE = "/views/managerPage.jsp";
    public static final String LOGIN = "/views/login.jsp";
    public static final String LIST_USERS = "/views/listUsers.jsp";
    public static final String LIST_USER_ORDERS = "/views/listUserOrders.jsp";
    public static final String INFO_UPDATE = "/views/infoUpdate.jsp";
    public static final String INFO_GET = "/views/infoGet.jsp";
    public static final String INFO_FORM = "/views/infoForm.jsp";
    public static final String HOME = "/views/home.jsp";
    public static final String ADMIN_PAGE = "/views/adminPage.jsp";
    public static final String ACCESS_DENIED = "/views/accessDenied.jsp";
    public static final String LIST_REQUESTS = "/views/listRequests.jsp";

    /**
     * URL Patterns for user
     */
    public static final List<String> USER_PATTERNS;
    /**
     * URL Patterns for manager
     */
    public static final List<String> MANAGER_PATTERNS;
    /**
     * URL Patterns for admin
     */
    public static final List<String> ADMIN_PATTERNS;


    static {
        USER_PATTERNS =  Arrays.asList("/userInfo","/userTask","/updateUser",
                "/listOrders","/updateUser","/infoGet","/infoForm","/infoUpdate","/order");

        MANAGER_PATTERNS = Arrays.asList("/userInfo","/managerTask","/orderForm",
                "/listUsers","/order","/allOrders", "/info","/infoGet","/listRequests","/orderSort");

        ADMIN_PATTERNS = Arrays.asList("/userInfo","/adminTask","/orderForm",
                "/listUsers","/order","/allOrders","/info","/infoGet","/admin","/orderSort");
    }

    private Path() {throw new UnsupportedOperationException("utility class");}
}
