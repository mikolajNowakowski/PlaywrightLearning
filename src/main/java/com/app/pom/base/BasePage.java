package com.app.pom.base;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;
import com.app.utils.extent_reports.manager.ExtentManager;
import com.app.utils.popups.PopupsManager;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;


@WebPage
public abstract class BasePage {

    @Value("${url.main}")
    private String mainUrl;

    @LazyAutowired
    @Qualifier("basePageLocators")
    protected LocatorsProperties basePageLocators;

    @LazyAutowired
    protected Browser browser;

    @Autowired
    protected Page page;

    @LazyAutowired
    protected ExtentManager extentManager;

    @Autowired
    private PopupsManager popupsManager;

    public <T extends BasePage> void waitForPopupClicking(Class<T> pageClass, String locatorKey, LocatorsProperties locatorsProperties) {
        Page newPage = page.waitForPopup(() -> {
            page.click(locatorsProperties.getLocator(locatorKey));
        });
        popupsManager.addPage(pageClass.getName(), newPage);
    }

    protected static final Logger logger = Logger.getLogger(BasePage.class);

    protected final String mainBarWishListButton = "mainBarWishListButton";
    protected final String mainBarShopButton = "mainBarShopButton";
    protected final String mainBarMyAccountButton = "mainBarMyAccountButton";


    public void goToMainUrl() {
        logger.info("navigating to main url");
        page.navigate(mainUrl);
    }



    // Get Element from Page
    protected Locator getElement(Page page, String locatorKey, LocatorsProperties locatorsProperties) {
        Locator element = null;
        try {
            element = page.locator(locatorsProperties.getLocator(locatorKey));
            extentManager.logInfo("Loading an element/s : " + locatorKey);
            return element;
        } catch (Throwable t) {
            extentManager.logFailure(
                    "Loading an element/s : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
        return element;
    }

    protected Locator getElement(String locatorKey, LocatorsProperties locatorsProperties) {
        return getElement(this.page, locatorKey, locatorsProperties);
    }



    // Click on web element
    protected void click(Page page, String locatorKey, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).click();
            extentManager.logInfo("Clicking on an Element : " + locatorKey);
        } catch (Throwable t) {
            extentManager.logFailure(
                    "Clicking on an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void click(String locatorKey, LocatorsProperties locatorsProperties) {
        this.click(this.page, locatorKey, locatorsProperties);
    }

    protected void click(Locator locator) {
        try {
            locator.click();
            extentManager.logInfo("Clicking on locator.");
        } catch (Throwable t) {
            extentManager.logFailure(
                    "Clicking on locator. Error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }



    // type
    protected void type(Page page, String locatorKey, String text, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).type(text);
            extentManager.logInfo("Typing in an Element : " + locatorKey + " with text: " + text);
        } catch (Throwable t) {
            extentManager.logFailure("Typing in an Element : " + locatorKey + " with text: " + text + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void type(String locatorKey, String text, LocatorsProperties locatorsProperties) {
        this.type(this.page, locatorKey, text, locatorsProperties);
    }



    // fill
    protected void fill(Page page, String locatorKey, String text, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).fill(text);
            extentManager.logInfo("Filling an Element : " + locatorKey + " with text: " + text);
        } catch (Throwable t) {
            extentManager.logFailure("Filling an Element : " + locatorKey + " with text: " + text + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void fill(String locatorKey, String text, LocatorsProperties locatorsProperties) {
        this.fill(this.page, locatorKey, text, locatorsProperties);
    }



    // Loading text from web element
    protected String getText(Page page, String locatorKey, LocatorsProperties locatorsProperties) {
        try {
            String text = page.locator(locatorsProperties.getLocator(locatorKey)).textContent();
            extentManager.logInfo("Getting text from an Element : " + locatorKey + " retrieved text: " + text);
            return text;
        } catch (Throwable t) {
            extentManager.logFailure("Getting text from an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
            return null;
        }
    }

    protected String getText(String locatorKey, LocatorsProperties locatorsProperties) {
        return this.getText(this.page, locatorKey, locatorsProperties);
    }



    // select option
    protected void selectOption(Page page, String locatorKey, String option, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).selectOption(option);
            extentManager.logInfo("Selecting option: " + option + " from an Element : " + locatorKey);
        } catch (Throwable t) {
            extentManager.logFailure("Selecting option: " + option + " from an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void selectOption(String locatorKey, String option, LocatorsProperties locatorsProperties) {
        this.selectOption(this.page, locatorKey, option, locatorsProperties);
    }



    // hover
    protected void hover(Page page, String locatorKey, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).hover();
            extentManager.logInfo("Hovering over an Element : " + locatorKey);
        } catch (Throwable t) {
            extentManager.logFailure("Hovering over an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void hover(String locatorKey, LocatorsProperties locatorsProperties) {
        this.hover(this.page, locatorKey, locatorsProperties);
    }



    // double click on element
    protected void doubleClick(Page page, String locatorKey, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).dblclick();
            extentManager.logInfo("Double clicking on an Element : " + locatorKey);
        } catch (Throwable t) {
            extentManager.logFailure("Double clicking on an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void doubleClick(String locatorKey, LocatorsProperties locatorsProperties) {
        this.doubleClick(this.page, locatorKey, locatorsProperties);
    }



    // Is element visible
    protected boolean isVisible(Page page, String locatorKey, LocatorsProperties locatorsProperties) {
        try {
            boolean visible = page.locator(locatorsProperties.getLocator(locatorKey)).isVisible();
            extentManager.logInfo("Checking visibility of an Element : " + locatorKey + " is visible: " + visible);
            return visible;
        } catch (Throwable t) {
            extentManager.logFailure("Checking visibility of an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
            return false;
        }
    }

    protected boolean isVisible(String locatorKey, LocatorsProperties locatorsProperties) {
        return this.isVisible(this.page, locatorKey, locatorsProperties);
    }



    // Wait for element
    protected void waitForSelector(Page page, String locatorKey, int timeout, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).waitFor(new Locator.WaitForOptions().setTimeout(timeout));
            extentManager.logInfo("Waiting for an Element : " + locatorKey + " with timeout: " + timeout);
        } catch (Throwable t) {
            extentManager.logFailure("Waiting for an Element : " + locatorKey + " with timeout: " + timeout + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void waitForSelector(String locatorKey, int timeout, LocatorsProperties locatorsProperties) {
        this.waitForSelector(this.page, locatorKey, timeout, locatorsProperties);
    }
}
