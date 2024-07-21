package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.pom.pages.LoginRegistrationPage;
import com.app.pom.pages.MainPage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RegistrationTest extends BaseTest {

    @LazyAutowired
    private MainPage mainPage;
    @LazyAutowired
    private LoginRegistrationPage loginRegistrationPage;

    @ParameterizedTest
    @CsvFileSource(resources = "/registration_test.csv", numLinesToSkip = 1)
    public void registration(String email, String password, boolean fakerEnabled) {
        if(fakerEnabled){
            email = fakerManager.generateEmail();
            password = fakerManager.generatePassword();
        }

        mainPage.goToMainUrl();
        mainPage.goToMyAccount();
        loginRegistrationPage.typeRegistrationMail(email);
        loginRegistrationPage.typeRegistrationPassword(password);

        loginRegistrationPage.submitRegistration();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
