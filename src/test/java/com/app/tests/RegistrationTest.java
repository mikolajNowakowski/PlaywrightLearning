package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.pom.MainPage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RegistrationTest extends BaseTest {

    @LazyAutowired
    private MainPage mainPage;

    @ParameterizedTest
    @CsvFileSource(resources = "/registration_test.csv", numLinesToSkip = 1)
    public void registration(String login, String password) {
        mainPage.goToMainUrl();
        mainPage.goToShop();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
