package com.twilio.manager;

import com.twilio.exceptions.PhoneNumberFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ivanbastidas on 2/6/15.
 */
public class PhoneCallManagerTest {

    @Test
    public void testCallDidNotHappen() throws Exception {
        PhoneCallManager pcm = new PhoneCallManager();
        pcm.phoneCallHappened("+1 1234567890123", "+12 123456789");
        Assert.assertEquals(pcm.didPhoneCallHappen("+12 123456789", "+1 1234567890123"), false);
    }

    @Test
    public void testCallHappened() throws Exception {
        PhoneCallManager pcm = new PhoneCallManager();
        pcm.phoneCallHappened("+1 1234567890", "+12 123456789");
        Assert.assertEquals(pcm.didPhoneCallHappen("+1 1234567890", "+12 123456789"), true);
    }

    @Test(expectedExceptions = PhoneNumberFormatException.class)
    public void testInvalidNumber() throws Exception {
        PhoneCallManager pcm = new PhoneCallManager();
        pcm.phoneCallHappened("1234567890", "+12 123456789");
    }
}
