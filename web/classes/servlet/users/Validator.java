package servlet.users;

/**
 * Created by VAblovatsky on 18.01.2017.
 */
public class Validator {

    private static boolean validPassword(String password, String confirmPassword){
        return password.equals(confirmPassword) ;
    }

    private static boolean validStr(String str) {
        return str.length() > 6 && str.length() < 18;
    }

    public static boolean isValid(String username, String password, String confirmPassword) {
        boolean valid = true;
        if (!validStr(username)) {
            valid = false;
        }
        if (!validStr(password)) {
            valid = false;
        }
        if (!validPassword(password, confirmPassword)) {
            valid = false;
        }

        return valid;
    }
}
