package exceptions;

public class DaoException extends RuntimeException {

    private static final long serialVersionUID = -8701662673949824535L;
    public DaoException(String massage){
        super(massage);
    }
}
