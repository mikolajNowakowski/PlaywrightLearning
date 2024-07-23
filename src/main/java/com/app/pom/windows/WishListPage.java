package com.app.pom.windows;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebWindow;
import com.app.model.locators.LocatorsProperties;
import com.app.pom.base.BasePage;
import com.app.utils.popups.PopupsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@WebWindow
public class WishListPage extends BasePage implements WebWindowInterface {
    private String wishListTitle = "wishListTitle";
    private String productsNamesOnWishList = "productsNamesOnWishList";
    private String productsRemoveButtons = "productsRemoveButtons";

    @LazyAutowired
    @Qualifier("wishListPageLocators")
    private LocatorsProperties wishListPageLocators;

    @Autowired
    private PopupsManager popupsManager;

    public WishListPage goToShop() {
        click(popupsManager.getPage(this.getClass().getName()), mainBarShopButton, basePageLocatorsProp);
        return this;
    }

    public WishListPage goToMyAccount() {
        click(popupsManager.getPage(this.getClass().getName()), mainBarMyAccountButton, basePageLocatorsProp);
        return this;
    }

    public WishListPage goToWishList() {
        click(popupsManager.getPage(this.getClass().getName()), mainBarWishListButton, basePageLocatorsProp);
        return this;
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
