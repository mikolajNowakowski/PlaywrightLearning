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
    public LocatorsProperties mainPageLocators() {
        String path = env.getRequiredProperty("mainPageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }

    @LazyBean
    @Qualifier("basePageLocators")
    public LocatorsProperties basePageLocators() {
        String path = env.getRequiredProperty("basePageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }

    @LazyBean
    @Qualifier("mainShopPageLocators")
    public LocatorsProperties MainShopPageLocators() {
        String path = env.getRequiredProperty("mainShopPageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }

    @LazyBean
    @Qualifier("loginRegistrationPageLocators")
    public LocatorsProperties LoginRegistrationPage() {
        String path = env.getRequiredProperty("loginRegistrationPageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }

    @LazyBean
    @Qualifier("wishListPageLocators")
    public LocatorsProperties wishListPageLocators() {
        String path = env.getRequiredProperty("wishListPageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }

    @LazyBean
    @Qualifier("myAccountPageLocators")
    public LocatorsProperties myAccountPageLocators() {
        String path = env.getRequiredProperty("myAccountPageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }

    @LazyBean
    @Qualifier("categoryPageLocators")
    public LocatorsProperties categoryPageLocators() {
        String path = env.getRequiredProperty("categoryPageLocators.path");
        return new LocatorsProperties(propertiesLoader.load(path));
    }

}
