package dao;

import config.SecurityConfig;
import constants.SQL;
import db.DataSource;
import entity.Request;
import entity.UserAccount;
import exceptions.DaoException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO used to access the table 'users' and 'requests'
 */
public class UserDao {
    private static Logger LOGGER = Logger.getLogger(UserDao.class);
    private static BasicDataSource dataSource;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static Connection connection;

    static {
        dataSource = DataSource.getInstance();
        pstmt = null;
        rs = null;
        connection = null;
    }

    /**
     * The method create a record in database table
     * if param 'role' equals null, set default role
     *
     * @param login    String
     * @param password String
     * @param role     String | null
     * @param phone    String
     * @param email    String
     * @return UserAccount
     * @throws DaoException checked exception wrapping SQLException
     */
    public static UserAccount createUser(String login, String password,
                                         String role, String phone, String email) throws DaoException {
        UserAccount account = new UserAccount();
        if (role == null) {
            role = SecurityConfig.ROLE_USER;
        }
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                account.setId(rs.getInt(1));
                account.setUserName(login);
                account.setRole(role);
                account.setPhone(phone);
                account.setEmail(email);
            }

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }

        return account;
    }

    /**
     * The method update a record in database table
     *
     * @param id       String
     * @param login    String
     * @param password String
     * @param phone    String
     * @param email    String
     * @return updated UserAccount
     * @throws DaoException checked exception wrapping SQLException
     */
    public static UserAccount updateUser(Integer id, String login, String password,
                                         String phone, String email) throws DaoException {
        UserAccount account = new UserAccount();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.UPDATE_USER);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.setString(3, SecurityConfig.ROLE_USER);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            account.setId(id);
            account.setUserName(login);
            account.setRole(SecurityConfig.ROLE_USER);
            account.setPhone(phone);
            account.setEmail(email);

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                rs.close();
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
        return account;
    }

    /**
     * The method deleted record by id in database table
     *
     * @param id UserAccount id
     * @throws DaoException checked exception wrapping SQLException
     */
    public static void delete(String id) throws DaoException {
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.REMOVE_USER);
            pstmt.setInt(1, Integer.valueOf(id));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                rs.close();
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    /**
     * The method finds record in database table with the specified userName & password
     *
     * @param userName String
     * @param password String
     * @return UserAccount
     * @throws DaoException checked exception wrapping SQLException
     */
    public static UserAccount findUser(String userName, String password) throws DaoException {
        UserAccount account = new UserAccount();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.FIND_USER_BY_ID_AND_PASSWORD);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                account = getUserAccount();
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                rs.close();
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
        return account;
    }

    /**
     * @return list of all records in database table
     * @throws DaoException checked exception wrapping SQLException
     */
    public static List<UserAccount> findAll() throws DaoException {
        List<UserAccount> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.GET_ALL_USERS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserAccount account = getUserAccount();
                list.add(account);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                rs.close();
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
        return list;
    }

    /**
     * The method set specified role in record with specified id
     *
     * @param role   String
     * @param userId String
     * @throws DaoException checked exception wrapping SQLException
     */
    public static void setRole(String role, String userId) throws DaoException {
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.SET_USER_ROLE);
            pstmt.setString(1, role);
            pstmt.setInt(2, Integer.valueOf(userId));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    /**
     * @return all record where role LIKE 'admin' or 'manager'
     * @throws DaoException checked exception wrapping SQLException
     */
    public static List<UserAccount> findAllAdministration() throws DaoException {
        List<UserAccount> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.GET_ALL_ADMINS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserAccount account = getUserAccount();
                list.add(account);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                rs.close();
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
        return list;
    }

    /**
     * Associates with the table 'request'
     * Method create a record in database table
     *
     * @param request_user_id  Integer
     * @param request_order_id String
     * @throws DaoException checked exception wrapping SQLException
     */
    public static void createRequest(Integer request_user_id, String request_order_id) throws DaoException {
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.MAKE_REQUEST, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, request_user_id);
            pstmt.setInt(2, Integer.valueOf(request_order_id));
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    /**
     * Associates with the table 'request'
     *
     * @return list of all records in database table
     * @throws DaoException checked exception wrapping SQLException
     */
    public static List<Request> findRequests() throws DaoException {
        List<Request> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.FIND_REQUESTS);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("id"));
                request.setUserId(rs.getInt("user_id"));
                request.setOrderId(rs.getInt("order_id"));
                list.add(request);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        } finally {
            try {
                rs.close();
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
        return list;
    }

    /**
     * private auxiliary method parse result set ​​and sets to UserAccount all values
     *
     * @return list of UserAccounts
     * @throws SQLException checked exception wrapping SQLException
     */
    private static UserAccount getUserAccount() throws SQLException {
        UserAccount account = new UserAccount();
        account.setId(rs.getInt("id"));
        account.setUserName(rs.getString("login"));
        account.setRole(rs.getString("role"));
        account.setPhone(rs.getString("phone"));
        account.setEmail(rs.getString("email"));
        return account;
    }
}
