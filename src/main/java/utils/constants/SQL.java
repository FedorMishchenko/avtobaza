package utils.constants;

/**
 * Utility class contains SQL queries
 */
public final class SQL {

    /**
     * SQL queries for Table 'users'
     */
    public static final String CREATE_USER = "INSERT INTO users VALUES(DEFAULT,?,?,?,?,?)";
    public static final String REMOVE_USER = "DELETE FROM users WHERE id=?";
    public static final String UPDATE_USER = "UPDATE users SET login = ?, password = ?," +
            " role=?,phone=?,email=? WHERE id=?;";
    public static final String GET_ALL_USERS = "SELECT * FROM users WHERE role LIKE 'user'";
    public static final String GET_ALL_ADMINS = "SELECT * FROM users WHERE role='manager' OR role='admin'";
    public static final String FIND_USER_BY_ID_AND_PASSWORD = "SELECT * FROM users WHERE login=? AND password=?";
    public static final String SET_USER_ROLE = "UPDATE users SET role=? WHERE id=?";

    /**
     * SQL queries for Table 'request'
     */
    public static final String MAKE_REQUEST = "INSERT INTO request VALUES (DEFAULT ,?,?)";
    public static final String FIND_REQUESTS = "SELECT * FROM request";
    public static final String DELETE_REQUEST = "DELETE FROM request WHERE user_id=? AND order_id=?";

    /**
     * SQL queries for Table 'orders'
     */
    public static final String CREATE_ORDER = "INSERT INTO orders " +
            "(id, start_point, destination, distance, status, creation_date, user_id)" +
            " VALUES (DEFAULT,?,?,?,?,DEFAULT,DEFAULT)";
    public static final String REMOVE_ORDER = "DELETE FROM orders WHERE id=?";
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    public static final String GET_ALL_ACTIVE_ORDERS = "SELECT * FROM orders WHERE status LIKE 'open'";
    public static final String GET_ALL_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE user_id=?";
    public static final String GET_ORDER_BYID = "SELECT * FROM orders WHERE id=?";
    public static final String CLOSE_ORDER = "UPDATE orders SET status = 'close' WHERE id = ?";
    public static final String APPROVE_ORDER = "UPDATE orders SET user_id = ?, status = 'progress' WHERE id = ?";

    /**
     * SQL queries for Table 'user_info'
     */
    public static final String CREATE_USER_INFO = "INSERT INTO user_info VALUES (DEFAULT,?,?,?,?)";
    public static final String FIND_INFO_BYID = "SELECT * FROM user_info WHERE user_id=?";
    public static final String UPDATE_USER_INFO = "UPDATE user_info SET truck = ?, status = ?, capacity = ? " +
            "WHERE user_id = ?";



    /**
     * private constructor, throw UnsupportedOperationException
     */
    private SQL(){
        throw new UnsupportedOperationException("utility class");
    }

}
