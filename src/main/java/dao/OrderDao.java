package dao;

import constants.SQL;
import db.DataSource;
import entity.Order;
import exceptions.DaoException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO used to access the table 'orders'
 */
public class OrderDao {
    private static Logger LOGGER = Logger.getLogger(OrderDao.class);
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
     *
     * @param startPoint  String
     * @param destination String
     * @param distance    String
     * @param status      String
     * @return Order
     * @throws DaoException checked exception wrapping SQLException
     */
    public static Order createOrder(String startPoint, String destination,
                                    String distance, String status) throws DaoException {
        Order order = new Order();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, startPoint);
            pstmt.setString(2, destination);
            pstmt.setString(3, distance);
            pstmt.setString(4, status);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getInt(1));
                order.setStartPoint(startPoint);
                order.setDestination(destination);
                order.setDistance(Integer.valueOf(distance));
                order.setStatus(status);
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
        return order;
    }

    /**
     * The method finds the record in database by the given id
     *
     * @param id Order id
     * @return Order
     * @throws DaoException checked exception wrapping SQLException
     */
    public static Order findOrder(Integer id) throws DaoException {
        Order order = new Order();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.GET_ORDER_BYID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                setValue(order);
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
        return order;
    }

    /**
     * The method delete record in database table by id
     *
     * @param id Order id
     * @throws DaoException checked exception wrapping SQLException
     */
    public static void delete(String id) throws DaoException {
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.REMOVE_ORDER);
            pstmt.setInt(1, Integer.valueOf(id));
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
     * The method finds all records in the table
     *
     * @return list Orders
     * @throws DaoException checked exception wrapping SQLException
     */
    public static List<Order> findAll() throws DaoException {
        List<Order> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.GET_ALL_ORDERS);
            createListOrders(list);
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
     * The method finds all records in the table where status equals 'open'
     *
     * @return list orders
     * @throws DaoException checked exception wrapping SQLException
     */
    public static List<Order> findAllOpen() throws DaoException {
        List<Order> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.GET_ALL_ACTIVE_ORDERS);
            createListOrders(list);
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
     * The method finds all records in the table linked with the given user id
     *
     * @param id User id
     * @return list Orders
     * @throws DaoException checked exception wrapping SQLException
     */
    public static List<Order> findAll(Integer id) throws DaoException {
        List<Order> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.GET_ALL_ORDERS_BY_USER_ID);
            pstmt.setInt(1, id);
            createListOrders(list);
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
     * The method set status LIKE 'close'
     *
     * @param id Order id
     * @throws DaoException checked exception wrapping SQLException
     */
    public static void closeOrder(String id) throws DaoException {
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.CLOSE_ORDER);
            pstmt.setInt(1, Integer.valueOf(id));
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
     * The method makes a transaction, first associates the order and user,
     * then deletes the request. If failed to delete, cancels the entire operation
     *
     * @param userId  User id
     * @param orderId Order id
     * @throws DaoException checked exception wrapping SQLException
     */
    public static void approveOrder(String userId, String orderId) throws DaoException {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(SQL.APPROVE_ORDER);
            pstmt.setInt(1, Integer.valueOf(userId));
            pstmt.setInt(2, Integer.valueOf(orderId));
            pstmt.executeUpdate();
            pstmt.clearParameters();
            pstmt = connection.prepareStatement(SQL.DELETE_REQUEST);
            pstmt.setInt(1, Integer.valueOf(userId));
            pstmt.setInt(2, Integer.valueOf(orderId));
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                /*NOP*/
            }
            throw new DaoException();
        } finally {
            try {
                pstmt.close();
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    /**
     * private auxiliary method
     *
     * @param list list Orders
     * @throws SQLException checked exception wrapping SQLException
     */
    private static void createListOrders(List<Order> list) throws SQLException {
        rs = pstmt.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            setValue(order);
            list.add(order);
        }
    }

    /**
     * private auxiliary method parse result set ​​and sets to Order all values
     *
     * @param order new Order with empty fields
     * @throws SQLException checked exception wrapping SQLException
     */
    private static void setValue(Order order) throws SQLException {
        order.setId(rs.getInt("id"));
        order.setStartPoint(rs.getString("start_point"));
        order.setDestination(rs.getString("destination"));
        order.setDistance(Integer.valueOf(rs.getString("distance")));
        order.setStatus(rs.getString("status"));
        order.setDate(rs.getString("creation_date"));
        order.setUserId(rs.getInt("user_id"));
    }
}
