package service;

import exceptions.DaoException;
import exceptions.ValidationException;
import dao.UserDao;
import entity.Request;
import entity.UserAccount;

import java.util.List;

import static utils.ValidatorUtils.*;

/**
 * Service used to transfer data from servlet to DAO associated with the table 'users' , 'request' and back.
 * Validates input parameters if needed
 */
public class UserService {

    public static UserAccount create(String login, String password,
                                     String role, String phone, String email) throws DaoException, ValidationException {
        if(isValid(login, password, role, phone, email)) {
            return UserDao.createUser(login, password, role, phone, email);
        }else {
            throw new ValidationException();
        }
    }

    public static UserAccount update(Integer id, String login, String password,
                                      String phone, String email) throws ValidationException, DaoException {
        if(isValid(id, login, password, phone, email)) {
            return UserDao.updateUser(id, login, password, phone, email);
        }else {
            throw new ValidationException();
        }
    }

    public static void delete(String id) throws ValidationException, DaoException {
        if(isValid(id)){
            UserDao.delete(id);
        }else {
            throw new ValidationException();
        }
    }

    public static UserAccount findUser(String userName, String password) throws ValidationException, DaoException {
        if(isValidLogin(userName, password)) {
            return UserDao.findUser(userName, password);
        }else {
            throw new ValidationException();
        }
    }

    public static List<UserAccount> findAll() throws DaoException {
        return UserDao.findAll();
    }

    public static void createRequest(Integer request_user_id, String request_order_id) throws DaoException {
        UserDao.createRequest(request_user_id, request_order_id);
    }

    public static List<Request> findRequests() throws DaoException {
        return UserDao.findRequests();
    }

    public static void setRole(String role, String userId) throws DaoException, ValidationException {
        if(isValidRole(role)) {
            UserDao.setRole(role, userId);
        }else {
            throw new ValidationException();
        }
    }

    public static List<UserAccount> findAllAdministration() throws DaoException {
        return UserDao.findAllAdministration();
    }
}
