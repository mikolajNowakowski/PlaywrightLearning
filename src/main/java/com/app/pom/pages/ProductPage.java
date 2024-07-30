package com.app.pom.pages;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;
import com.app.pom.base.BasePage;
import org.springframework.beans.factory.annotation.Qualifier;

@WebPage
public class ProductPage extends BasePage {

    @LazyAutowired
    private LocatorsProperties productPageLocators;

    private final String productTitle = "productTitle";

    private final String addToWishListButton = "addToWishListButton";

    private final String elementAddedToWishListConfirmation = "elementAddedToWishListConfirmation";

    public ProductPage addProductToWishList() {
        click(addToWishListButton, productPageLocators);
        return this;
    }


    public ProductPage addProductToWishListWithConfirmation() {
        click(addToWishListButton, productPageLocators);
        page.waitForSelector(productPageLocators.getLocator(elementAddedToWishListConfirmation));
        return this;
    }

}
