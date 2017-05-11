package com.wishes.validator;

import com.wishes.entities.User;
import com.wishes.services.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {

    static Pattern pattern;
    static Matcher matcher;

    private static final String Name_REGEX = "^(?!\\s*$|\\s).*$";


    public static boolean validate(String name){
        pattern = Pattern.compile(Name_REGEX);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static String CheckName(User user, UserService userService) {
        if (userService.isUserExists(user))
            return "User with email" + user.getEmail() + "already exists";
        if (!UsernameValidator.validate(user.getName()))
            return "Name can't be blank";
        return "User " + user.getName() + " "+ user.getEmail() + " created successfully";
    }
}
