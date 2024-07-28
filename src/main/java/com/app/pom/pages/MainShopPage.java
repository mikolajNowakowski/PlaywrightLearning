package com.app.pom.pages;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;
import com.app.pom.base.BasePage;
import org.springframework.beans.factory.annotation.Qualifier;

@WebPage
public class MainShopPage extends BasePage {

    @LazyAutowired
    @Qualifier("mainShopPageLocators")
    private LocatorsProperties mainShopPageLocators;


    private final String categories = "categories";

    public MainShopPage clickOnSpecificCategory(String categoryName) {
        getElement(categories, mainShopPageLocators)
                .all()
                .forEach(element -> System.out.println(element.innerText()));

        var category = getElement(categories, mainShopPageLocators)
                .all()
                .stream()
                .filter(element -> element.innerText().trim().toLowerCase().contains(categoryName.toLowerCase()))
                .findFirst()
                .orElseThrow();

        click(category);

        return this;
    }


}
