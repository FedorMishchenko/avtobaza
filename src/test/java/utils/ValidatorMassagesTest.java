package utils;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Validation tests of application input parameters
 */
public class ValidatorMassagesTest {

    @Test
    public void isValid() {
        String userId = "1";
        String orderId = "1";


        Assert.assertTrue(ValidatorUtils.isValid(userId, orderId));
        Assert.assertTrue(ValidatorUtils.isValid("1234567890", orderId));
        Assert.assertTrue(ValidatorUtils.isValid(userId, "1234567890"));


        Assert.assertFalse(ValidatorUtils.isValid("-1", "-5"));
        Assert.assertFalse(ValidatorUtils.isValid("", orderId));
        Assert.assertFalse(ValidatorUtils.isValid(" ", orderId));
        Assert.assertFalse(ValidatorUtils.isValid("  123", orderId));
        Assert.assertFalse(ValidatorUtils.isValid("1234567890" + 11, orderId));
        Assert.assertFalse(ValidatorUtils.isValid(userId, "1234567890" + 11));
        Assert.assertFalse(ValidatorUtils.isValid(userId + "a", orderId));
        Assert.assertFalse(ValidatorUtils.isValid(userId + ".", orderId));
        Assert.assertFalse(ValidatorUtils.isValid("123" + "-", orderId));
        Assert.assertFalse(ValidatorUtils.isValid(null, orderId));
        Assert.assertFalse(ValidatorUtils.isValid(userId, null));
    }

    @Test
    public void isValid1() {
        String userId = "1";


        Assert.assertTrue(ValidatorUtils.isValid(userId));
        Assert.assertTrue(ValidatorUtils.isValid("12345678901"));


        Assert.assertFalse(ValidatorUtils.isValid("-1"));
        Assert.assertFalse(ValidatorUtils.isValid(""));
        Assert.assertFalse(ValidatorUtils.isValid(" "));
        Assert.assertFalse(ValidatorUtils.isValid("  123"));
        Assert.assertFalse(ValidatorUtils.isValid("12345678901" + 1));
        Assert.assertFalse(ValidatorUtils.isValid(userId + "a"));
        Assert.assertFalse(ValidatorUtils.isValid(userId + "."));
        Assert.assertFalse(ValidatorUtils.isValid(userId + "_-&^%$"));
        Assert.assertFalse(ValidatorUtils.isValid(null));
        Assert.assertFalse(ValidatorUtils.isValid("NaN"));

    }

    @Test
    public void isValid2() {
        String truck = "abc";
        String status = "ready"; //ready|progress|repair
        String capacity = "1";


        Assert.assertTrue(ValidatorUtils.isValid(truck, status, capacity));
        Assert.assertTrue(ValidatorUtils.isValid(truck, "progress", capacity));
        Assert.assertTrue(ValidatorUtils.isValid(truck, "repair", capacity));
        Assert.assertTrue(ValidatorUtils.isValid("abc.-01", status, "12345678901"));


        Assert.assertFalse(ValidatorUtils.isValid(truck, "abc", capacity));
        Assert.assertFalse(ValidatorUtils.isValid(truck, status, ""));
        Assert.assertFalse(ValidatorUtils.isValid(truck, status, " "));
        Assert.assertFalse(ValidatorUtils.isValid(truck, "", capacity));
        Assert.assertFalse(ValidatorUtils.isValid(truck, " ", capacity));
        Assert.assertFalse(ValidatorUtils.isValid("", status, capacity));
        Assert.assertFalse(ValidatorUtils.isValid(" ", status, capacity));
        Assert.assertFalse(ValidatorUtils.isValid(truck, status, "12345678901" + 1));
        Assert.assertFalse(ValidatorUtils.isValid(null, status, capacity));
        Assert.assertFalse(ValidatorUtils.isValid(truck, null, capacity));
        Assert.assertFalse(ValidatorUtils.isValid(truck, status, null));
    }

    @Test
    public void isValid3() {
        String login = "abc";
        String password = "abc";
        String role = "user"; //admin|manager|user
        String phone = "1234567890";
        String email = "abc@example.com";


        Assert.assertTrue(ValidatorUtils.isValid(login,password,role,phone,email));
        Assert.assertTrue(ValidatorUtils.isValid(login,password,role,"(123)4567890",email));
        Assert.assertTrue(ValidatorUtils.isValid(login,password,role,"(123)456-7890",email));
        Assert.assertTrue(ValidatorUtils.isValid(login,password,role,"123-456-7890" ,email));
        Assert.assertTrue(ValidatorUtils.isValid(login,password,"admin",phone,email));
        Assert.assertTrue(ValidatorUtils.isValid(login,password,"manager",phone,email));
        Assert.assertTrue(ValidatorUtils.isValid(login,password,role,phone,"a-b@example.com"));
        Assert.assertTrue(ValidatorUtils.isValid(login,password,role,phone,"123@example.com"));
        Assert.assertTrue(ValidatorUtils.isValid(login,password,role,phone,"123@example123.com"));


        Assert.assertFalse(ValidatorUtils.isValid("логин",password,role,phone,email));
        Assert.assertFalse(ValidatorUtils.isValid(login,"пароль",role,phone,email));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,role,phone,"имейл@пример.ком"));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,"role",phone,email));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,"123",phone,email));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,role,phone,"123@123"));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,role,phone,"123@com"));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,role,phone,"abc@com"));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,role,phone,""));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,role,phone," "));
        Assert.assertFalse(ValidatorUtils.isValid("","","","",""));
        Assert.assertFalse(ValidatorUtils.isValid(" "," "," "," "," "));
        login = null;
        Assert.assertFalse(ValidatorUtils.isValid(login,password,role,phone,email));
        login = "abc";
        Assert.assertFalse(ValidatorUtils.isValid(login,null,role,phone,email));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,null,phone,email));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,role,null,email));
        Assert.assertFalse(ValidatorUtils.isValid(login,password,role,phone,null));
    }

    @Test
    public void isValid4() {
        Integer id = 22;
        String login = "user";
        String password = "password123";
        String phone = "1234567890";
        String email = "user@example.com";

        Assert.assertTrue(ValidatorUtils.isValid(id,login,password,phone,email));

        Assert.assertFalse(ValidatorUtils.isValid(-2,login,password,phone,email));
        id = null;
        Assert.assertFalse(ValidatorUtils.isValid(id,login,password,phone,email));
        id = 22;
        Assert.assertFalse(ValidatorUtils.isValid(id,null,password,phone,email));
        Assert.assertFalse(ValidatorUtils.isValid(id,login,null,phone,email));
        Assert.assertFalse(ValidatorUtils.isValid(id,login,password,null,email));
        Assert.assertFalse(ValidatorUtils.isValid(id,login,password,phone,null));

    }

    @Test
    public void isValid5() {
        String startPoint = "abc";
        String destination = "abc";
        String distance = "123";
        String status = "open"; //open|progress|close|cancel


        Assert.assertTrue(ValidatorUtils.isValid(startPoint,destination, distance, status));
        Assert.assertTrue(ValidatorUtils.isValid(startPoint,destination, distance, "progress"));
        Assert.assertTrue(ValidatorUtils.isValid(startPoint,destination, distance, "close"));
        Assert.assertTrue(ValidatorUtils.isValid(startPoint,destination, distance, "cancel"));
        Assert.assertTrue(ValidatorUtils.isValid(startPoint + 123,destination, distance, status));
        Assert.assertTrue(ValidatorUtils.isValid(startPoint,destination + 123, distance, status));


        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, distance + "aaa", status));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, "-100", status));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, distance, "aaa"));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, distance, "100"));
        Assert.assertFalse(ValidatorUtils.isValid(null,destination, distance, status));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,null, distance, status));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, null, status));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, distance, null));
        Assert.assertFalse(ValidatorUtils.isValid("",destination, distance, status));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,"", distance, status));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, "", status));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, distance, ""));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, distance, " "));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint,destination, " ", status));
        Assert.assertFalse(ValidatorUtils.isValid(startPoint," ", distance, status));
        Assert.assertFalse(ValidatorUtils.isValid(" ",destination, distance, status));


    }

    @Test
    public void isValidLogin() {
        String name = "user";
        String password = "password";


        Assert.assertTrue(ValidatorUtils.isValidLogin(name, password));
        Assert.assertTrue(ValidatorUtils.isValidLogin("123", password));
        Assert.assertTrue(ValidatorUtils.isValidLogin(name, "123"));
        Assert.assertTrue(ValidatorUtils.isValidLogin(name + 123, password + 123));
        Assert.assertTrue(ValidatorUtils.isValidLogin(name, password + "_.-"));
        Assert.assertTrue(ValidatorUtils.isValidLogin("aaa", password));
        Assert.assertTrue(ValidatorUtils.isValidLogin(name, "aaa"));


        Assert.assertFalse(ValidatorUtils.isValidLogin("", ""));
        Assert.assertFalse(ValidatorUtils.isValidLogin(" ", " "));
        Assert.assertFalse(ValidatorUtils.isValidLogin("  www", "  www"));
        Assert.assertFalse(ValidatorUtils.isValidLogin(name + "_.-", password));
        Assert.assertFalse(ValidatorUtils.isValidLogin("Имя", "пароль"));
        Assert.assertFalse(ValidatorUtils.isValidLogin("abc!-%", password));
        Assert.assertFalse(ValidatorUtils.isValidLogin(name, "!&^"));
        Assert.assertFalse(ValidatorUtils.isValidLogin(name, "aa"));
        Assert.assertFalse(ValidatorUtils.isValidLogin("aa", password));
        Assert.assertFalse(ValidatorUtils.isValidLogin(null, password));
        Assert.assertFalse(ValidatorUtils.isValidLogin(name, null));

    }
}