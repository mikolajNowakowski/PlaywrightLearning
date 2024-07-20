package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.pom.LoginRegistrationPage;
import com.app.pom.MainPage;

import com.app.pom.MainShopPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RegistrationTest extends BaseTest {

    @LazyAutowired
    private MainPage mainPage;
    @LazyAutowired
    private LoginRegistrationPage loginRegistrationPage;
    @LazyAutowired
    private Faker faker;

    @ParameterizedTest
    @CsvFileSource(resources = "/registration_test.csv", numLinesToSkip = 1)
    public void registration(String login, String password, boolean fakerEnabled) {
        if(fakerEnabled){
            login = faker.internet().emailAddress();
            password = faker.internet().password(10,15,true,true,true);
        }
        mainPage.goToMainUrl();
        mainPage.goToMyAccount();
        loginRegistrationPage.typeRegistrationMail(login);
        loginRegistrationPage.typeRegistrationPassword(password);

        loginRegistrationPage.submitRegistration();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
