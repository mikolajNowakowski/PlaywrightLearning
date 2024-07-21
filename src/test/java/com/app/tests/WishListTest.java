package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.pom.pages.MainPage;
import com.app.pom.windows.WishListPage;
import org.junit.jupiter.api.Test;

public class WishListTest extends BaseTest {


    @LazyAutowired
    private MainPage mainPage;
    @LazyAutowired
    private WishListPage wishListPage;


    @Test
    public void wishListTest() {
        mainPage.goToMainUrl();
        mainPage.goToWishList();
        wishListPage.goToMyAccount();
      wishListPage.closeWindow();
        mainPage.goToMyAccount();
    }

}
