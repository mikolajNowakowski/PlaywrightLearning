package com.app.tests.login_registration;

import com.app.annotations.LazyAutowired;
import com.app.annotations.MultiLineDescription;
import com.app.pom.pages.LoginRegistrationPage;
import com.app.pom.pages.MainPage;

import com.app.pom.pages.MyAccountPage;

import com.app.tests.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RegistrationTest extends BaseTest {

    @LazyAutowired
    private MainPage mainPage;
    @LazyAutowired
    private LoginRegistrationPage loginRegistrationPage;
    @LazyAutowired
    private MyAccountPage myAccountPage;


    @MultiLineDescription("""
            1. Go to main url
            2. Go to my account page
            3. Provide email into registration email element
            4. Provide password into registration password element
            5. Submit registration of new user
            6. Verify that user is logged and registered now
            """)
    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/login_registration/registration_test.csv", numLinesToSkip = 1)
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
        assertionTrue(myAccountPage.hasSpecifiedName(email.split("@")[0]));
    }
}
