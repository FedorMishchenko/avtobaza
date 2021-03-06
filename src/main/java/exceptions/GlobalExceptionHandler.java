package exceptions;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

import static constants.Massages.*;
import static constants.Massages.ERROR_VALIDATION;

/**
 * Exception handler
 */
public final class GlobalExceptionHandler implements Serializable {
    private static final long serialVersionUID = -5534069821651483450L;
    private static Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    private GlobalExceptionHandler() {
        throw new UnsupportedOperationException();
    }

    /**
     * The method handle ValidationException & DaoException
     * adds to request error massage
     * Logger writes to the console interception exception message
     *
     * @param e       Exception
     * @param request HttpServletRequest request
     */
    public static void handleException(Exception e, HttpServletRequest request) {
        if (e.getClass().getSimpleName().equals("DaoException")) {
            LOGGER.warn(DAOEXCEPTION_MASSAGE);
            request.getSession().setAttribute("errorMassage", ERROR_MASSAGE);
        } else {
            LOGGER.warn(VALIDATION_EXCEPTION_MASSAGE);
            request.getSession().setAttribute("errorMassage", ERROR_VALIDATION);
        }
    }
}
