package com.app.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

public class FirstTest extends BaseTest {

@Autowired
    private Properties storeHomePageLocatorsProp;

    @Test
    public void test() {
        System.out.println(storeHomePageLocatorsProp.getProperty("key1"));
    }

}
