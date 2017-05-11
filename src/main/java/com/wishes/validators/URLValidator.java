package com.wishes.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLValidator {

    static Pattern pattern;
    static Matcher matcher;

    private static final String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";


    public static boolean validate(String URL){
        pattern = Pattern.compile(URL_REGEX);
        matcher = pattern.matcher(URL);
        return matcher.matches();
    }

}
