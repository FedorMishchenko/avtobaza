package service;

import exceptions.ValidationException;
import model.dao.OrderDao;
import model.entity.Order;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static utils.ValidatorUtils.MASSAGE;
import static utils.ValidatorUtils.isValid;

/**
 * Service used to transfer data from servlet to DAO associated with the table 'orders' and back.
 * Validates input parameters and sort the response if needed
 */
public class OrderService {

    public static Order create(String startPoint, String destination,
                               String distance, String status,String user_id){
        if(isValid(startPoint, destination, distance, status)){
            return OrderDao.createOrder(startPoint, destination, distance, status, user_id);
        }else {
            throw new ValidationException(MASSAGE);
        }
    }

    public static Order findOrder(Integer id){
        return OrderDao.findOrder(id);
    }

    public static void delete(String id){
        if(isValid(id)) {
            OrderDao.delete(id);
        }else {
            throw new ValidationException(MASSAGE);
        }
    }

    public static List<Order> findAll(){
        return OrderDao.findAll();
    }

    public static List<Order> findAllOpen(){
        return OrderDao.findAllOpen();
    }

    public static List<Order> findAll(Integer id){
        return OrderDao.findAll(id);
    }


    public static List<Order> findAllSortedByID() {
        return OrderDao.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .collect(Collectors.toList());
    }

    public static List<Order> findAllSortedByDistance() {
        return OrderDao.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getDistance))
                .collect(Collectors.toList());
    }

    public static List<Order> findAllSortedByDate() {

        return OrderDao.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getDate).reversed())
                .collect(Collectors.toList());
    }

    public static void closeOrder(String id) {
        OrderDao.closeOrder(id);
    }

    public static void approveOrder(String userId, String orderId) {
        OrderDao.approveOrder(userId, orderId);
    }

    public static List<Order> findAllSortedByUserID() {
        return OrderDao.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getUserId))
                .collect(Collectors.toList());
    }
}
