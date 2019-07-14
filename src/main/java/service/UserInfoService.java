package service;

import exceptions.DaoException;
import exceptions.ValidationException;
import dao.UserInfoDao;
import entity.UserInfo;

import static utils.ValidatorUtils.isValid;

/**
 * Service used to transfer data from servlet to DAO associated with the table 'user_info' and back.
 * Validates input parameters if needed
 */
public class UserInfoService {

    public static UserInfo create(String truck, String status,
                                  String capacity, Integer userID) throws DaoException, ValidationException {
        if(isValid(truck, status, capacity)){
            return UserInfoDao.create(truck,status,capacity,userID);
        }else {
            throw new ValidationException();
        }
    }

    public static UserInfo update(String truck, String status, String capacity, Integer userID) throws DaoException,
            ValidationException {
        if(isValid(truck, status, capacity)){
            return UserInfoDao.update(truck,status,capacity,userID);
        }else {
            throw new ValidationException();
        }
    }

    public static UserInfo findInfoByID(Integer id) throws DaoException {
        return UserInfoDao.findInfoByID(id);
    }
}
