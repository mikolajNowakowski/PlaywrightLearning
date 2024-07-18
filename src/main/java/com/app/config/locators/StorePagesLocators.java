package com.app.config.locators;

import com.app.annotations.LazyAutowired;
import com.app.annotations.LazyBean;
import com.app.annotations.LazyConfiguration;
import com.app.model.locators.LocatorsProperties;
import com.app.utils.loaders.properties.PropertiesLoader;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;

@LazyConfiguration
public class StorePagesLocators {

    @LazyAutowired
    private Environment env;

    @LazyAutowired
    private PropertiesLoader propertiesLoader;

    @LazyBean
    @Qualifier("mainPageLocators")
    public LocatorsProperties mainPageLocatorsProp() {
        String path = env.getRequiredProperty("storeMainPageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }

    @LazyBean
    @Qualifier("basePageLocators")
    public LocatorsProperties basePageLocatorsProp() {
        String path = env.getRequiredProperty("basePageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }
}
