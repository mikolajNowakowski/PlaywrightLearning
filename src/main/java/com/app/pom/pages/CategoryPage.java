package com.app.pom.pages;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;
import com.app.pom.base.BasePage;
import org.springframework.beans.factory.annotation.Qualifier;

@WebPage
public class CategoryPage extends BasePage {

    @LazyAutowired
    @Qualifier("categoryPageLocators")
    private LocatorsProperties categoryPageLocators;

    private final String products = "products";

    private final String cartButton = "cartButton";

    private final String numberOfProductsInCart = "numberOfProductsInCart";

    private final String productsTitles = "productsTitles";

    private final String titleOfProductLocator = "//h2[contains(text(),'%s')]";



    private final String addToCartButtonLinkedText = "Dodaj do koszyka";
    private final String addToCartButtonLocator = "//a[text()='%s']".formatted(addToCartButtonLinkedText);


    public CategoryPage clickOnSpecificProduct(String productName) {
        var productsFromCategory = getElement(productsTitles, categoryPageLocators).all();
        var productToClick = productsFromCategory
                .stream()
                .filter(product -> product
                        .innerText().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow();

        productToClick.scrollIntoViewIfNeeded();

        click(productToClick);

        return this;
    }


    public CategoryPage addSpecificProductToCart(String productName){
       var product = getElement(products,categoryPageLocators).all().stream().filter(element -> !element.locator(titleOfProductLocator.formatted(productName)).all().isEmpty()).findFirst().orElseThrow();
      var button = product.locator(addToCartButtonLocator);
      click(button);
        return this;
    }


}
