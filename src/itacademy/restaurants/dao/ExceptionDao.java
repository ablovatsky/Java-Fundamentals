package itacademy.restaurants.dao;

/**
 * Created by aVa on 11.01.2017.
 */

public class ExceptionDao extends RuntimeException {

    public ExceptionDao() {
        super();
    }

    public ExceptionDao(String message) {
        super(message);
    }

    public ExceptionDao(String message, Throwable cause) {
        super(message, cause);
    }
}
