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
    private LocatorsProperties mainPageLocators;

    protected final String mainBarWishListButton = "mainBarWishListButton";
    protected final String mainBarShopButton = "mainBarShopButton";
    protected final String mainBarMyAccountButton = "mainBarMyAccountButton";


    public void goToShop() {
        click( mainBarShopButton, mainPageLocators);
    }

    public void goToMyAccount() {
        click( mainBarMyAccountButton, mainPageLocators);
    }

    public void goToWishList() {
        waitForPopupClicking(WishListPage.class,mainBarWishListButton, mainPageLocators);
    }




}
