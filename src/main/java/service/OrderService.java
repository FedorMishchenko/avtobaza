package service;

import exceptions.DaoException;
import exceptions.ValidationException;
import model.dao.OrderDao;
import model.entity.Order;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static utils.ValidatorUtils.isValid;

/**
 * Service used to transfer data from servlet to DAO associated with the table 'orders' and back.
 * Validates input parameters and sort the response if needed
 */
public class OrderService {

    public static Order create(String startPoint, String destination,
                               String distance, String status) throws DaoException, ValidationException {
        if(isValid(startPoint, destination, distance, status)){
            return OrderDao.createOrder(startPoint, destination, distance, status);
        }else {
            throw new ValidationException();
        }
    }

    public static Order findOrder(Integer id) throws DaoException {
        return OrderDao.findOrder(id);
    }

    public static void delete(String id) throws DaoException, ValidationException {
        if(isValid(id)) {
            OrderDao.delete(id);
        }else {
            throw new ValidationException();
        }
    }

    public static List<Order> findAll() throws DaoException {
        return OrderDao.findAll();
    }

    public static List<Order> findAllOpen() throws DaoException {
        return OrderDao.findAllOpen();
    }

    public static List<Order> findAll(Integer id) throws DaoException {
        return OrderDao.findAll(id);
    }


    public static List<Order> findAllSortedByID() throws DaoException {
        return OrderDao.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .collect(Collectors.toList());
    }

    public static List<Order> findAllSortedByDistance() throws DaoException {
        return OrderDao.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getDistance))
                .collect(Collectors.toList());
    }

    public static List<Order> findAllSortedByDate() throws DaoException {

        return OrderDao.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getDate).reversed())
                .collect(Collectors.toList());
    }

    public static void closeOrder(String id) throws DaoException {
        OrderDao.closeOrder(id);
    }

    public static void approveOrder(String userId, String orderId) throws DaoException {
        OrderDao.approveOrder(userId, orderId);
    }

    public static List<Order> findAllSortedByUserID() throws DaoException {
        return OrderDao.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getUserId))
                .collect(Collectors.toList());
    }
}
