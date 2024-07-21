package com.app.pom.windows;

import com.app.annotations.WebWindow;
import com.app.pom.base.BasePage;
import com.app.utils.popups.PopupsManager;
import org.springframework.beans.factory.annotation.Autowired;

@WebWindow
public class WishListPage extends BasePage {

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

    public void closeWindow() {
        popupsManager.getPage(this.getClass().getName()).close();
        popupsManager.deletePage(this.getClass().getName());
        page.bringToFront();
    }
}
