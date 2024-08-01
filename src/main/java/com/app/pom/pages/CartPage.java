package com.app.pom.pages;

import com.app.annotations.LazyAutowired;
import com.app.annotations.LazyBean;
import com.app.model.locators.LocatorsProperties;
import org.springframework.beans.factory.annotation.Qualifier;

public class CartPage {

    @LazyAutowired
    @Qualifier("cartPageLocators")
    private LocatorsProperties cartPageLocators;

    private String productsInCart = "productsInCart";
    private String emptyCartAnnouncement = "emptyCartAnnouncement";
    private String deletedProductAnnouncement = "deletedProductAnnouncement";
    private String goToPaymentButton = "goToPaymentButton";
    private String productNameXpath = "productNameXpath";
    private String productDeleteButtonXpath = "productDeleteButtonXpath";







}
