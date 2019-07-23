package constants;

/**
 * Utility class contains regExp used to validate input parameters
 */
public final class Regexp {
    public static final String EMAIL_REGEX = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    /*
     * matches abc123@example.com
     * matches abc@example.com
     * matches 123@example.com
     * matches a-c123@example.com
     * matches 123@example123.com
     * */
    public static final String PHONE_REGEX = "^\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}$";
    /*     regex:
     * matches 1234567890
     * matches 123-456-7890
     * matches (123)456-7890 or (123)4567890
     * */
    public static final String DIGITS_REGEX = "^(\\d{1,11})$";
    /*
     * matches positive numbers ranging from 1 to 999 999 999 99
     *
     * */
    public static final String WORDS_REGEX = "^([\\w]{3,15})$";
    public static final String PASSWORD_REGEX = "^([a-zA-Z0-9_.-]{3,15})$";
    public static final String ORDER_STATUS_REGEX = "(^)(open|progress|close|cancel)($)";
    public static final String INFO_STATUS_REGEX = "(^)(ready|progress|repair)($)";
    public static final String ROLE_REGEX = "(^)(admin|manager|user)($)";

    private Regexp() {
        throw new UnsupportedOperationException("utility class");
    }
}