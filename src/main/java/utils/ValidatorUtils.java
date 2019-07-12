package utils;

import constants.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {

    public static final String MASSAGE = "invalid input data";

    public static boolean isValid(String userId, String orderId) {
        if (userId == null || orderId == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(Regexp.DIGITS_REGEX).matcher(orderId);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.DIGITS_REGEX).matcher(userId);
        return matcher.matches();
    }

    public static boolean isValid(String userId) {
        if (userId == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(Regexp.DIGITS_REGEX).matcher(userId);
        return matcher.matches();
    }

    public static boolean isValid(String truck, String status,
                                  String capacity) {
        if (truck == null || status == null || capacity == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(Regexp.PASSWORD_REGEX).matcher(truck);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.INFO_STATUS_REGEX).matcher(status);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.DIGITS_REGEX).matcher(capacity);
        return matcher.matches();
    }

    public static boolean isValid(String login, String password,
                                  String role, String phone, String email) {
        if (login == null || password == null || role == null ||
                phone == null || email == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(Regexp.PASSWORD_REGEX).matcher(login);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.PASSWORD_REGEX).matcher(password);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.ROLE_REGEX).matcher(role);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.PHONE_REGEX).matcher(phone);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.EMAIL_REGEX).matcher(email);
        return matcher.matches();
    }

    public static boolean isValid(Integer id, String login, String password,
                                  String phone, String email) {
        if (id == null || login == null || password == null ||
                phone == null || email == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(Regexp.DIGITS_REGEX).matcher(String.valueOf(id));
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.PASSWORD_REGEX).matcher(login);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.PASSWORD_REGEX).matcher(password);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.PHONE_REGEX).matcher(phone);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.EMAIL_REGEX).matcher(email);
        return matcher.matches();
    }

    public static boolean isValid(String startPoint, String destination, String distance, String status) {
        if (startPoint == null || destination == null || distance == null ||
                status == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(Regexp.WORDS_REGEX).matcher(startPoint);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.WORDS_REGEX).matcher(destination);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.DIGITS_REGEX).matcher(distance);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.ORDER_STATUS_REGEX).matcher(status);
        return matcher.matches();
    }

    public static boolean isValidLogin(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(Regexp.WORDS_REGEX).matcher(login);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(Regexp.PASSWORD_REGEX).matcher(password);
        return matcher.matches();
    }
}
