package com.app.pom.pages;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;

import com.app.pom.base.BasePage;
import com.app.pom.windows.WishListPage;
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

    public MainPage goToMyAccount(){
        click(mainBarMyAccountButton,basePageLocatorsProp);
        return this;
    }

    public MainPage goToWishList(){
        waitForPopupClicking(WishListPage.class,mainBarWishListButton,basePageLocatorsProp);
        return this;
    }



}
