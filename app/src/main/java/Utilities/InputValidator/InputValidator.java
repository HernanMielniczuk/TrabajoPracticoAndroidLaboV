package Utilities.InputValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hernan on 29/04/2017.
 */

public class InputValidator {

    private InputValidator(){ }

    public static boolean isValidEmail(String email){
        if(!stringContainsCharacters(email)){
            return false;
        }

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password){
            return stringContainsCharacters(password);
        }

    private static boolean stringContainsCharacters(String value) {
        return value.trim().length() > 0;
    }
}
