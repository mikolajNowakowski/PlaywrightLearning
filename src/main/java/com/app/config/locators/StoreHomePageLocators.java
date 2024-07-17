package com.app.config.locators;

import com.app.annotations.LazyAutowired;
import com.app.annotations.LazyBean;
import com.app.annotations.LazyConfiguration;
import com.app.model.locators.LocatorsProperties;
import com.app.utils.loaders.properties.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import java.util.Properties;

@LazyConfiguration
public class StoreHomePageLocators {

    @LazyAutowired
    private Environment env;

    @LazyAutowired
    private PropertiesLoader propertiesLoader;

    @LazyBean
    public LocatorsProperties storeHomePageLocatorsProp() {
        String path = env.getRequiredProperty("storeMainPageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }


    @LazyBean
    public LocatorsProperties basePageLocatorsProp() {
        String path = env.getRequiredProperty("basePageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }
}
