package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.pom.pages.LoginRegistrationPage;
import com.app.pom.pages.MainPage;

import com.app.pom.pages.MyAccountPage;

import com.app.tests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RegistrationTest extends BaseTest {

    @LazyAutowired
    private MainPage mainPage;
    @LazyAutowired
    private LoginRegistrationPage loginRegistrationPage;
    @LazyAutowired
    private MyAccountPage myAccountPage;

    @ParameterizedTest
    @CsvFileSource(resources = "/registration_test.csv", numLinesToSkip = 1)
    @Tag("REGISTRATION")
    public void registrationTest(String email, String password, boolean fakerEnabled) {
        if(fakerEnabled){
            email = fakerManager.generateEmail();
            password = fakerManager.generatePassword();
        }

        mainPage.goToMainUrl();
        mainPage.goToMyAccount();
        loginRegistrationPage.typeRegistrationMail(email);
        loginRegistrationPage.typeRegistrationPassword(password);
        loginRegistrationPage.submitRegistration();
        assertionTrue(myAccountPage.hasSpecifiedName(email.split("@")[1]));
    }
}
