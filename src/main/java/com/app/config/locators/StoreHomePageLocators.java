package com.app.config.locators;

import com.app.annotations.LazyAutowired;
import com.app.annotations.LazyBean;
import com.app.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import java.util.Properties;

@Configuration
public class StoreHomePageLocators {

    @Autowired
    private Environment env;

    @LazyAutowired
    private PropertiesLoader propertiesLoader;

    @LazyBean
    public Properties storeHomePageLocatorsProp() {
        String path = env.getRequiredProperty("storeHomePageLocators.path");
        System.out.println(path);
        return propertiesLoader.loadProperties(path);
    }
}
