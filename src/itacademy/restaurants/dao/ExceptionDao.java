package itacademy.restaurants.dao;


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
