package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static config.SecurityConfig.*;
import static constants.Regexp.*;

/**
 * Utility class used for validation input data
 */
public final class ValidatorUtils {

    public static boolean isValid(String userId, String orderId) {
        if (userId == null || orderId == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(DIGITS_REGEX).matcher(orderId);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(DIGITS_REGEX).matcher(userId);
        return matcher.matches();
    }

    public static boolean isValid(String userId) {
        if (userId == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(DIGITS_REGEX).matcher(userId);
        return matcher.matches();
    }

    public static boolean isValid(String truck, String status,
                                  String capacity) {
        if (truck == null || status == null || capacity == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(PASSWORD_REGEX).matcher(truck);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(INFO_STATUS_REGEX).matcher(status);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(DIGITS_REGEX).matcher(capacity);
        return matcher.matches();
    }

    public static boolean isValid(String login, String password,
                                  String role, String phone, String email) {
        if (login == null || password == null || role == null ||
                phone == null || email == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(PASSWORD_REGEX).matcher(login);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(PASSWORD_REGEX).matcher(password);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(ROLE_REGEX).matcher(role);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(PHONE_REGEX).matcher(phone);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(EMAIL_REGEX).matcher(email);
        return matcher.matches();
    }

    public static boolean isValid(Integer id, String login, String password,
                                  String phone, String email) {
        if (id == null || login == null || password == null ||
                phone == null || email == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(DIGITS_REGEX).matcher(String.valueOf(id));
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(PASSWORD_REGEX).matcher(login);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(PASSWORD_REGEX).matcher(password);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(PHONE_REGEX).matcher(phone);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(EMAIL_REGEX).matcher(email);
        return matcher.matches();
    }

    public static boolean isValid(String startPoint, String destination, String distance, String status) {
        if (startPoint == null || destination == null || distance == null ||
                status == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(WORDS_REGEX).matcher(startPoint);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(WORDS_REGEX).matcher(destination);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(DIGITS_REGEX).matcher(distance);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(ORDER_STATUS_REGEX).matcher(status);
        return matcher.matches();
    }

    public static boolean isValidLogin(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        Matcher matcher = Pattern.compile(WORDS_REGEX).matcher(login);
        if (!matcher.matches()) {
            return false;
        }
        matcher.reset();
        matcher = Pattern.compile(PASSWORD_REGEX).matcher(password);
        return matcher.matches();
    }

    public static boolean isValidRole(String role){
        return role.equals(ROLE_USER) || role.equals(ROLE_MANAGER)
                || role.equals(ROLE_ADMIN);
    }
}
