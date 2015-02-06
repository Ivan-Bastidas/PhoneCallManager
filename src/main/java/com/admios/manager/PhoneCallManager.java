package com.admios.manager;

import com.admios.exceptions.PhoneNumberFormatException;
import com.admios.utils.Helpers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PhoneCallManager {

    /*
    * Using ConcurrentMap because it allows concurrent modification of the Map from several threads
    * without the need to block them
    */
    private ConcurrentMap<String, Boolean> phoneCalls = new ConcurrentHashMap<String, Boolean>();
    private static PhoneCallManager single = new PhoneCallManager();

    private PhoneCallManager() {
    }

    public static PhoneCallManager getInstance() {
        return single;
    }

    private boolean findCall(final String from, final String to) {
        return phoneCalls.containsKey(Helpers.stringifyCall(from, to));
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
    public boolean didPhoneCallHappen(final String from, final String to) throws PhoneNumberFormatException {
        if (!Helpers.validateNumber(from)) {
            throw new PhoneNumberFormatException(from);
        }
        if (!Helpers.validateNumber(to)) {
            throw new PhoneNumberFormatException(to);
        }
        return findCall(from, to);
    }

    /*
    * Return whether a phone call has happened between the 2 numbers for the lifetime
    * of this application in any order between the numbers
    */
    public boolean didPhoneCallHappenAnyOrder(final String numberA, final String numberB) throws PhoneNumberFormatException {
        if (!Helpers.validateNumber(numberA)) {
            throw new PhoneNumberFormatException(numberA);
        }
        if (!Helpers.validateNumber(numberB)) {
            throw new PhoneNumberFormatException(numberB);
        }
        return findCall(numberA, numberB) || findCall(numberB, numberA);
    }

    /*
    * Removes all the recorded calls
    */
    public void clearData() {
        phoneCalls.clear();
    }
}