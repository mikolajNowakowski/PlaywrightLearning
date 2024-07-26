package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.pom.pages.MainPage;
import com.app.pom.windows.WishListPage;
import com.app.tests.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class WishListTest extends BaseTest {


    @LazyAutowired
    private MainPage mainPage;
    @LazyAutowired
    private WishListPage wishListPage;


    @Test
    @Tag("WISH_LIST")
    public void wishListTest() {
        mainPage.goToMainUrl();
        mainPage.goToWishList();
        wishListPage.goToMyAccount();
        assertionTrue(false);
     // wishListPage.closeWindow();
        mainPage.goToMyAccount();
    }

}
