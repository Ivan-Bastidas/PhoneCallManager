package com.twilio.manager;

import com.twilio.exceptions.PhoneNumberFormatException;
import com.twilio.utils.Helpers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PhoneCallManager {

    private ConcurrentMap<String, Boolean> phoneCalls = new ConcurrentHashMap<String, Boolean>();
    private static PhoneCallManager single = new PhoneCallManager();

    private PhoneCallManager() {
    }

    public static PhoneCallManager getInstance() {
        return single;
    }

    /*
    * Record the fact that a phone call happened between 2 numbers, this will
    * be called by some other class that actually does the phone call
    */
    public void phoneCallHappened(final String from, final String to) throws PhoneNumberFormatException {
        if (!Helpers.validateNumber(from)) {
            throw new PhoneNumberFormatException(from);
        }
        if (!Helpers.validateNumber(to)) {
            throw new PhoneNumberFormatException(to);
        }
        phoneCalls.put(Helpers.stringifyCall(from, to), true);
    }

    /*
    * Return whether a phone call has happened between the 2 numbers for the lifetime
    * of this application
    */
    public boolean didPhoneCallHappen(final String from, final String to) {
        return phoneCalls.containsKey(Helpers.stringifyCall(from, to));
    }

    public void clearData() {
        phoneCalls.clear();
    }
}