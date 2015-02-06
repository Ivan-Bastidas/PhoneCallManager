package com.admios.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ivanbastidas on 2/6/15.
 */
public class Helpers {

    /*
    * Creates a single String with the numbers in the format from->to
    */
    public static String stringifyCall(final String from, final String to) {
        return String.format("%s->%s", from, to);
    }

    /*
    * Validates that the number is in the correct format (ITU-T standards)
    */
    public static boolean validateNumber(String number) {
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
