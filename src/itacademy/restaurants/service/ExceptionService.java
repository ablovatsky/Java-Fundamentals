package itacademy.restaurants.service;

/**
 * Created by aVa on 11.01.2017.
 */

public class ExceptionService extends Exception {

    public ExceptionService() {
        super();
    }

    public ExceptionService(String message) {
        super(message);
    }

    public ExceptionService(String message, Throwable cause) {
        super(message, cause);
    }
}
