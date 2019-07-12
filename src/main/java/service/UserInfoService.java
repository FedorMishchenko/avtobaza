package service;

import model.dao.UserInfoDao;
import model.entity.UserInfo;

/**
 * Service used to transfer data from servlet to DAO associated with the table 'user_info' and back.
 * Validates input parameters if needed
 */
public class UserInfoService {

    public static UserInfo create(String truck, String status,
                                  String capacity, Integer userID){
        /*if(isValid(truck, status, capacity)){*/
            return UserInfoDao.create(truck,status,capacity,userID);
       /* }else {
            throw new ValidationException(MASSAGE);
        }*/
    }

    public static UserInfo update(String truck, String status, String capacity, Integer userID){
      /*  if(isValid(truck, status, capacity)){*/
            return UserInfoDao.update(truck,status,capacity,userID);
       /* }else {
            throw new ValidationException(MASSAGE);
        }*/
    }

    public static UserInfo findInfoByID(Integer id){
        return UserInfoDao.findInfoByID(id);
    }
}
