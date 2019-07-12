package service;

import exceptions.ValidationException;
import model.dao.UserDao;
import model.entity.Request;
import model.entity.UserAccount;

import java.util.List;

import static utils.ValidatorUtils.*;

/**
 * Service used to transfer data from servlet to DAO associated with the table 'users' , 'request' and back.
 * Validates input parameters if needed
 */
public class UserService {

    public static UserAccount create(String login, String password,
                                     String role, String phone, String email){
        if(isValid(login, password, role, phone, email)) {
            return UserDao.createUser(login, password, role, phone, email);
        }else {
            throw new ValidationException(MASSAGE);
        }
    }

    public static UserAccount update(Integer id, String login, String password,
                                      String phone, String email){
        if(isValid(id, login, password, phone, email)) {
            return UserDao.updateUser(id, login, password, phone, email);
        }else {
            throw new ValidationException(MASSAGE);
        }
    }

    public static void delete(String id){
        if(isValid(id)){
            UserDao.delete(id);
        }else {
            throw new ValidationException(MASSAGE);
        }
    }

    public static UserAccount findUser(String userName, String password){
        if(isValidLogin(userName, password)) {
            return UserDao.findUser(userName, password);
        }else {
            throw new ValidationException(MASSAGE);
        }
    }

    public static List<UserAccount> findAll(){
        return UserDao.findAll();
    }

    public static void createRequest(Integer request_user_id, String request_order_id) {
        UserDao.createRequest(request_user_id, request_order_id);
    }

    public static List<Request> findRequests() {
        return UserDao.findRequests();
    }

    public static void setRole(String role, String userId) {
        UserDao.setRole(role, userId);
    }

    public static List<UserAccount> findAllAdministration() {
        return UserDao.findAllAdministration();
    }
}
