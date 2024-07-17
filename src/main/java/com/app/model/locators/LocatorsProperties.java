package com.app.model.locators;

import java.util.Properties;

public class LocatorsProperties {
    private final Properties locators;

    public LocatorsProperties(Properties locators) {
        this.locators = locators;
    }

    public String getLocator(String key){
        return locators.getProperty(key);
    }

}
