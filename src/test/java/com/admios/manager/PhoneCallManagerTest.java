package com.admios.manager;

import com.admios.exceptions.PhoneNumberFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ivanbastidas on 2/6/15.
 */
public class PhoneCallManagerTest {

    @Test
    public void testCallDidNotHappen() throws Exception {
        PhoneCallManager pcm = PhoneCallManager.getInstance();
        pcm.clearData();
        pcm.phoneCallHappened("+1 1234567890123", "+12 123456789");
        Assert.assertEquals(pcm.didPhoneCallHappen("+12 123456789", "+1 1234567890123"), false);
    }

    @Test
    public void testCallHappened() throws Exception {
        PhoneCallManager pcm = PhoneCallManager.getInstance();
        pcm.clearData();
        pcm.phoneCallHappened("+1 1234567890", "+12 123456789");
        Assert.assertEquals(pcm.didPhoneCallHappen("+1 1234567890", "+12 123456789"), true);
    }

    @Test
    public void testCallHappenedAnyOrder() throws Exception {
        PhoneCallManager pcm = PhoneCallManager.getInstance();
        pcm.clearData();
        pcm.phoneCallHappened("+1 1234567890", "+12 123456789");
        Assert.assertEquals(pcm.didPhoneCallHappenAnyOrder("+12 123456789", "+1 1234567890"), true);
    }

    @Test(expectedExceptions = PhoneNumberFormatException.class)
    public void testInvalidNumber() throws Exception {
        PhoneCallManager pcm = PhoneCallManager.getInstance();
        pcm.clearData();
        pcm.phoneCallHappened("1234567890", "+12 123456789");
    }
}
