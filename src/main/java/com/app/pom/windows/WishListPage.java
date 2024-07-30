package com.app.pom.windows;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebWindow;
import com.app.model.locators.LocatorsProperties;
import com.app.pom.base.BasePage;
import com.app.utils.popups.PopupsManager;
import com.microsoft.playwright.Locator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@WebWindow
public class WishListPage extends BasePage implements WebWindowInterface {
    private final String wishListTitle = "wishListTitle";
    private final String productsNamesOnWishList = "productsNamesOnWishList";
    private final String productsRemoveButtons = "productsRemoveButtons";

    @LazyAutowired
    @Qualifier("wishListPageLocators")
    private LocatorsProperties wishListPageLocators;

    @Autowired
    private PopupsManager popupsManager;


    public List<String> getProductNamesFromWishList(){
        return getElement(popupsManager.getPage(this.getClass().getName()), productsNamesOnWishList,wishListPageLocators).all().stream().map(Locator::innerText).toList();
    }


    public boolean isSpecifiedNumberOfProductOnWishList(int number){
        return getElement(popupsManager.getPage(this.getClass().getName()), productsNamesOnWishList,wishListPageLocators).all().size() == number;
    }

    @Override
    public void closeWindow() {
        popupsManager.getPage(this.getClass().getName()).close();
        popupsManager.deletePage(this.getClass().getName());
    }

    @Override
    public void setPageAsFront() {

        popupsManager.getPage(this.getClass().getName()).bringToFront();
    }
}
