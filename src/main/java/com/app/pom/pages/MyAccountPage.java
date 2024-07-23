package com.app.pom.pages;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;
import com.app.pom.base.BasePage;
import org.springframework.beans.factory.annotation.Qualifier;

@WebPage
public class MyAccountPage extends BasePage {

    private String loggedUserName = "loggedUserName";
    private String logOutButton = "logOutButton";
    private String storeLogo = "storeLogo";

    @LazyAutowired
    @Qualifier("myAccountPageLocators")
    private LocatorsProperties myAccountPageLocators;

    public boolean hasSpecifiedName(String name) {
        return getElement(loggedUserName, myAccountPageLocators).innerText().contains(name);
    }


}
