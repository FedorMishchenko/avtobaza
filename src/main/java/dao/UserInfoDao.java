package dao;

import constants.SQL;
import db.DataSource;
import exceptions.DaoException;
import entity.UserInfo;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * DAO used to access the table 'User_info'
 */
public class UserInfoDao {
    private static Logger LOGGER = Logger.getLogger(UserInfoDao.class);
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
     * @param truck    String
     * @param status   String
     * @param capacity String
     * @param userID   String
     * @return UserInfo
     * @throws DaoException checked exception wrapping SQLException
     */
    public static UserInfo create(String truck, String status,
                                  String capacity, Integer userID) throws DaoException {
        UserInfo userInfo = new UserInfo();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.CREATE_USER_INFO,
                    Statement.RETURN_GENERATED_KEYS);
            setValue(truck, status, capacity, userID);
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                userInfo.setId(rs.getInt(1));
                userInfo.setTruck(truck);
                userInfo.setStatus(status);
                userInfo.setCapacity(capacity);
                userInfo.setUserID(userID);
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

        return userInfo;
    }

    /**
     * The method finds a record by id in database table
     *
     * @param id Integer
     * @return UserInfo
     * @throws DaoException checked exception wrapping SQLException
     */
    public static UserInfo findInfoByID(Integer id) throws DaoException {
        UserInfo info = new UserInfo();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.FIND_INFO_BYID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                info.setId(rs.getInt("id"));
                info.setTruck(rs.getString("truck"));
                info.setStatus(rs.getString("status"));
                info.setCapacity(rs.getString("capacity"));
                info.setUserID(id);
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
        return info;
    }

    /**
     * The method updates the record in table with specified values
     *
     * @param truck    String
     * @param status   String
     * @param capacity String
     * @param userID   String
     * @return updated UserInfo
     * @throws DaoException checked exception wrapping SQLException
     */
    public static UserInfo update(String truck, String status, String capacity, Integer userID) throws DaoException {
        UserInfo info = new UserInfo();
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL.UPDATE_USER_INFO);
            setValue(truck, status, capacity, userID);
            info.setTruck(truck);
            info.setStatus(status);
            info.setCapacity(capacity);
            info.setUserID(userID);
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
        return info;
    }

    /**
     * private auxiliary method parse result set ​​and sets to Order all values
     *
     * @throws SQLException which will be wrapped by DaoException
     */
    private static void setValue(String truck, String status, String capacity, Integer userID) throws SQLException {
        pstmt.setString(1, truck);
        pstmt.setString(2, status);
        pstmt.setString(3, capacity);
        pstmt.setInt(4, userID);
        pstmt.executeUpdate();
    }

}
