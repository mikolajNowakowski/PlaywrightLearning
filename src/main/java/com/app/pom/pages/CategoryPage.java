package com.app.pom.pages;

import com.app.annotations.LazyAutowired;
import com.app.model.locators.LocatorsProperties;
import com.app.pom.base.BasePage;
import org.springframework.beans.factory.annotation.Qualifier;

public class CategoryPage extends BasePage {

    @LazyAutowired
    @Qualifier("categoryPageLocators")
    private LocatorsProperties categoryPageLocators;

    private String products = "products";

    private String cartButton = "cartButton";

    private String numberOfProductsInCart = "numberOfProductsInCart";

    private String productsTitles = "productsTitles";

    private final String titleOFProductLocator = "//h2[contains(text(),'%s')]";

    private final String addToCartButtonLinkedText = "Dodaj do koszyka";


}
