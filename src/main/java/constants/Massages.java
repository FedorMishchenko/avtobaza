package constants;

/**
 * Utility class contains error massages
 */
public final class Massages {
    public static final String ERROR_MASSAGE = "SORRY I CAN'T PERFORM THE OPERATION, TRY AGAIN LATER";
    public static final String ERROR_VALIDATION = "INVALID INPUT DATA";
    public static final String ORDER_FORM_MASSAGE = "ORDER CREATED SUCCESSFUL";
    public static final String INVALID_USER_ID = "USER WITH THIS ID DOES NOT EXIST";

    public static final String DAOEXCEPTION_MASSAGE = "DaoException intercepted and processed";
    public static final String VALIDATION_EXCEPTION_MASSAGE = "ValidationException intercepted and processed";

    private Massages(){throw new UnsupportedOperationException("Utility class");}
}
