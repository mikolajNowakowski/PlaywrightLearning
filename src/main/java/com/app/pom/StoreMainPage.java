package com.app.pom;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Properties;

@WebPage
public  class StoreMainPage extends BasePage {

    @Autowired
    private LocatorsProperties storeHomePageLocatorsProp;




}
