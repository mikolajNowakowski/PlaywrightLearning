package com.app.tests.assertions;

import com.app.annotations.LazyAutowired;
import com.app.utils.extent_reports.manager.ExtentManager;
import org.junit.jupiter.api.Assertions;
import org.springframework.stereotype.Component;


public abstract class LocalAssertions {
    @LazyAutowired
    protected ExtentManager extentManager;

    protected void assertionTrue(boolean result) {
        try {
            extentManager.logInfo("Checking that result is: true");
            Assertions.assertTrue(result);
            extentManager.logSuccess("Assertion PASSED ! ! !");
        } catch (Throwable t) {
            extentManager.logFailure("ERROR: value is different from expected. -> " + t.getMessage());
            Assertions.fail(t.getMessage());
        }
    }

    protected void assertionFalse(boolean result) {
        try {
            extentManager.logInfo("Checking that result is: false");
            Assertions.assertFalse(result);
            extentManager.logSuccess("Assertion PASSED ! ! !");
        } catch (Throwable t) {
            extentManager.logFailure("ERROR: value is different from expected. -> " + t.getMessage());
            Assertions.fail(t.getMessage());
        }
    }

    protected void assertionEquals(Object expected, Object actual) {
        try {
            extentManager.logInfo("Checking that actual value equals expected value");
            Assertions.assertEquals(expected, actual);
            extentManager.logSuccess("Assertion PASSED ! ! !");
        } catch (Throwable t) {
            extentManager.logFailure("ERROR: value is different from expected. -> " + t.getMessage());
            Assertions.fail(t.getMessage());
        }
    }

    protected void assertionNotEquals(Object unexpected, Object actual) {
        try {
            extentManager.logInfo("Checking that actual value does not equal unexpected value");
            Assertions.assertNotEquals(unexpected, actual);
            extentManager.logSuccess("Assertion PASSED ! ! !");
        } catch (Throwable t) {
            extentManager.logFailure("ERROR: value is different from expected. -> " + t.getMessage());
            Assertions.fail(t.getMessage());
        }
    }

    protected void assertionNotNull(Object object) {
        try {
            extentManager.logInfo("Checking that object is not null");
            Assertions.assertNotNull(object);
            extentManager.logSuccess("Assertion PASSED ! ! !");
        } catch (Throwable t) {
            extentManager.logFailure("ERROR: object is null. -> " + t.getMessage());
            Assertions.fail(t.getMessage());
        }
    }

    protected void assertionNull(Object object) {
        try {
            extentManager.logInfo("Checking that object is null");
            Assertions.assertNull(object);
            extentManager.logSuccess("Assertion PASSED ! ! !");
        } catch (Throwable t) {
            extentManager.logFailure("ERROR: object is not null. -> " + t.getMessage());
            Assertions.fail(t.getMessage());
        }
    }

}
