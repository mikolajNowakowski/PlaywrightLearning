package com.app.pom;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;

import org.springframework.beans.factory.annotation.Qualifier;

@WebPage
public  class MainPage extends BasePage {

    @LazyAutowired
    @Qualifier("mainPageLocators")
    private LocatorsProperties mainPageLocatorsProp;


    public MainPage goToShop(){
        click(mainBarShopButton,basePageLocatorsProp);
        return this;
    }





}
