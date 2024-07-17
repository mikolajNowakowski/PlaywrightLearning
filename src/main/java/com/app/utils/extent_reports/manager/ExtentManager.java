package com.app.utils.extent_reports.manager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExtentManager {

    @Autowired
    private ExtentReports extentReports;
    private ExtentTest extentTest;


    public void startTest(String testName) {
        this.extentTest = extentReports.createTest(testName);
    }

    public ExtentTest getTest() {
        return extentTest;
    }

    public void logInfo(String message) {
        extentTest.log(Status.INFO, message);
    }

    public void logWarning(String message) {
        extentTest.log(Status.WARNING, message);
    }

    public void logSuccess(String message) {
        extentTest.log(Status.PASS, message);
    }

    public void logFailure(String message) {
        extentTest.log(Status.FAIL, message);
    }

    public void flushReports() {
        extentReports.flush();
    }
}
