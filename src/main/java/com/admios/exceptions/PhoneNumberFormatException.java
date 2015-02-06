package com.admios.exceptions;

/**
 * Created by ivanbastidas on 2/6/15.
 */
public class PhoneNumberFormatException extends Exception {

    public PhoneNumberFormatException(String number) {
        super(String.format("The number '%s' is not using a valid phone number format", number));
    }
}
