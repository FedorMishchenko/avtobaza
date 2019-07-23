package utils;

import entity.UserAccount;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public final class AppUtils {

    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_uri_map = new HashMap<>();
    private static final Map<String, Integer> uri_id_map = new HashMap<>();

    /**
     * The method Stores user information in HttpSession
     *
     * @param session     HttpSession
     * @param loginedUser UserAccount
     */
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    /**
     * The method Gets user information stored in HttpSession
     *
     * @param session HttpSession
     * @return UserAccount
     */
    public static UserAccount getLoginedUser(HttpSession session) {
        return (UserAccount) session.getAttribute("loginedUser");
    }

    /**
     * The method stored information used to access and redirect to web pages
     *
     * @param requestUri String
     * @return id
     */
    public static int storeRedirectAfterLoginUrl(String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;
            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }
        return id;
    }

    /**
     * The method gets information used to access and redirect to web pages
     *
     * @param redirectId id
     * @return stored url or null
     */
    static String getRedirectAfterLoginUrl(int redirectId) {
        String url = id_uri_map.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }

}
