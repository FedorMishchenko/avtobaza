package exceptions;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

import static utils.constants.Massages.*;
import static utils.constants.Massages.ERROR_VALIDATION;

public final class GlobalExceptionHandler implements Serializable {
    private static final long serialVersionUID = -5534069821651483450L;
    private static Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    private GlobalExceptionHandler(){ throw new UnsupportedOperationException();}

    public static void handleException(Exception e, HttpServletRequest request){
        if (e.getClass().getSimpleName().equals("DaoException")) {
            LOGGER.warn(DAOEXCEPTION_MASSAGE);
            request.getSession().setAttribute("errorMassage", ERROR_MASSAGE);
        } else {
            LOGGER.warn(VALIDATION_EXCEPTION_MASSAGE);
            request.getSession().setAttribute("errorMassage", ERROR_VALIDATION);
        }
    }
}
