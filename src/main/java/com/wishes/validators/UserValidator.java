package com.wishes.validators;

import com.wishes.entities.User;
import com.wishes.services.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    static Pattern pattern;
    static Matcher matcher;

    private static final String Name_REGEX = "^(?!\\s*$|\\s).*$";

    public static boolean validate(String object,String REGEX){
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(object);
        return matcher.matches();
    }

    public static String Check(User user, UserService userService) {
        if (!UserValidator.validate(user.getName(),Name_REGEX))
            return "Name can't be blank";
        if (userService.isUserExists(user)==true)
            return "User with email " + user.getEmail() + " already exists";

        return "Incorrect email";
    }
    public static String success(User user){
        return "User " + user.getName() + " "+ user.getEmail() + " created successfully";
    }
    public static boolean correct(User user,UserService userService){
        if (UserValidator.validate(user.getName(),Name_REGEX) && userService.isUserExists(user)==false)
            return true;
        return false;
    }
}
