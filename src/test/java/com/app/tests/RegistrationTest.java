package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.pom.StoreMainPage;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RegistrationTest extends BaseTest {

    @LazyAutowired
    private StoreMainPage storeMainPage;

    @ParameterizedTest
    @CsvFileSource(resources = "/registration_test.csv", numLinesToSkip = 1)
    public void registration(String login, String password) {
        logger.info("DUPSKOOOO");
        extentManager.logInfo("Dupkaaa");
        System.out.println(login);
        System.out.println(password);
        storeMainPage.goToMainUrl();
    }

}
