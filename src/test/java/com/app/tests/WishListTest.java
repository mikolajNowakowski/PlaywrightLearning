package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.pom.pages.CategoryPage;
import com.app.pom.pages.MainPage;
import com.app.pom.pages.MainShopPage;
import com.app.pom.pages.ProductPage;
import com.app.pom.windows.WishListPage;
import com.app.tests.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class WishListTest extends BaseTest {


    @LazyAutowired
    private MainPage mainPage;
    @LazyAutowired
    private WishListPage wishListPage;
    @LazyAutowired
    private MainShopPage mainShopPage;
    @LazyAutowired
    private CategoryPage categoryPage;
    @LazyAutowired
    private ProductPage productPage;


    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/wish_list/product_to_wish_list_test.csv", numLinesToSkip = 1)
    @Tag("WISH_LIST")
    public void wishListTest(String category, String productName) {
        mainPage.goToMainUrl();
        mainPage.goToShop();
        mainShopPage.clickOnSpecificCategory(category);
        categoryPage.clickOnSpecificProduct(productName);
        productPage.addProductToWishListWithConfirmation();

        mainPage.goToWishList();
        assertionTrue(wishListPage.isSpecifiedNumberOfProductOnWishList(1));

        var productOnWishList = wishListPage.getProductNamesFromWishList();
        assertionEquals(productName,productOnWishList.get(0));


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
