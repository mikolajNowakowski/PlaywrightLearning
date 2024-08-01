package com.app.tests.purchase;

import com.app.annotations.LazyAutowired;
import com.app.annotations.MultiLineDescription;
import com.app.pom.pages.CategoryPage;
import com.app.pom.pages.MainPage;
import com.app.pom.pages.MainShopPage;
import com.app.tests.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ProductPurchaseTest extends BaseTest {

    @LazyAutowired
    private MainPage mainPage;
    @LazyAutowired
    private MainShopPage mainShopPage;
    @LazyAutowired
    private CategoryPage categoryPage;

@MultiLineDescription("""
        adsasdasd
        """)
@Tag("Purchase")
    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/purchase/purchase_one_product_test.csv", numLinesToSkip = 1)
    public void purchaseOneProductTest(String category, String productName) {

        mainPage.goToMainUrl();
        mainPage.goToShop();
        mainShopPage.clickOnSpecificCategory(category);
        categoryPage.addSpecificProductToCart(productName);

        // TODO działa, dodac sprawdzenie i czekanie aż produkt doda sie do koszyka

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
