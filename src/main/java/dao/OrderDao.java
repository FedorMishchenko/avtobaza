package dao;

import constants.SQL;
import db.DataSource;
import exceptions.DaoException;
import entity.Order;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        return list.stream()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .collect(Collectors.toList());
    }

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
        return list.stream()
                .distinct()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .collect(Collectors.toList());
    }

    public static void closeOrder(String id) throws DaoException {
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.CLOSE_ORDER);
            pstmt.setInt(1, Integer.valueOf(id));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new DaoException();
        }finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

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
        }finally {
            try {
                pstmt.close();
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createListOrders(List<Order> list) throws SQLException {
        rs = pstmt.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            setValue(order);
            list.add(order);
        }
    }

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
