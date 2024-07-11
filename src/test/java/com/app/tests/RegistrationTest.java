package com.app.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

public class FirstTest extends BaseTest {

@Autowired
    private Properties storeHomePageLocatorsProp;

    @ParameterizedTest
    @CsvFileSource(resources ="/firstData.csv", numLinesToSkip = 1)
    public void test(String imie, String nazwisko, int age) {

    }

}
