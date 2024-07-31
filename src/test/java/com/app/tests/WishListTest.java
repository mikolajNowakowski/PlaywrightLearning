package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.annotations.MultiLineDescription;
import com.app.pom.pages.CategoryPage;
import com.app.pom.pages.MainPage;
import com.app.pom.pages.MainShopPage;
import com.app.pom.pages.ProductPage;
import com.app.pom.windows.WishListPage;
import com.app.tests.base.BaseTest;
import com.app.utils.string.StringUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.app.utils.string.StringUtils.*;

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



    @MultiLineDescription("""
        1. Go to main url
        2. Go to shop page
        3. Go to specific category
        4. Open specific product
        5. Add specific product to the wish list and wait for it to be completed
        6. Go to wish list
        7. Check if added product from step 5 is on wish list
        """)
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
        assertionEquals(removeSpecialCharsFromString(productName),removeSpecialCharsFromString(productOnWishList.get(0)));
    }

}
