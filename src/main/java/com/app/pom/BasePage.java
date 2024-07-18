package com.app.pom;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;
import com.app.utils.extent_reports.manager.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;


@WebPage
public abstract class BasePage {
    @Value("${url.main}")
    private String mainUrl;
    @LazyAutowired
    private LocatorsProperties basePageLocatorsProp;
    @LazyAutowired
    protected Browser browser;
    @LazyAutowired
    protected Page page;
    @LazyAutowired
    protected ExtentManager extentManager;


    protected static final Logger logger = Logger.getLogger(BasePage.class);

    protected String mainBarWishListButton = "mainBarWishListButton";
    protected String mainBarShopButton = "mainBarShopButton";
    protected String mainBarMyAccountButton = "mainBarMyAccountButton";

    public void goToMainUrl() {
        logger.info("navigating to main url");
        page.navigate(mainUrl);
    }

    protected void click(String locatorKey, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).click();
            extentManager.logInfo("Clicking on an Element : " + locatorKey);
        } catch (Throwable t) {
            extentManager.logFailure(
                    "Clicking on an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void type(String locatorKey, String text, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).type(text);
            extentManager.logInfo("Typing in an Element : " + locatorKey + " with text: " + text);
        } catch (Throwable t) {
            extentManager.logFailure("Typing in an Element : " + locatorKey + " with text: " + text + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected String getText(String locatorKey, LocatorsProperties locatorsProperties) {
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

    protected void selectOption(String locatorKey, String option, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).selectOption(option);
            extentManager.logInfo("Selecting option: " + option + " from an Element : " + locatorKey);
        } catch (Throwable t) {
            extentManager.logFailure("Selecting option: " + option + " from an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void hover(String locatorKey, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).hover();
            extentManager.logInfo("Hovering over an Element : " + locatorKey);
        } catch (Throwable t) {
            extentManager.logFailure("Hovering over an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected void doubleClick(String locatorKey, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).dblclick();
            extentManager.logInfo("Double clicking on an Element : " + locatorKey);
        } catch (Throwable t) {
            extentManager.logFailure("Double clicking on an Element : " + locatorKey + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    protected boolean isVisible(String locatorKey, LocatorsProperties locatorsProperties) {
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

    protected void waitForSelector(String locatorKey, int timeout, LocatorsProperties locatorsProperties) {
        try {
            page.locator(locatorsProperties.getLocator(locatorKey)).waitFor(new Locator.WaitForOptions().setTimeout(timeout));
            extentManager.logInfo("Waiting for an Element : " + locatorKey + " with timeout: " + timeout);
        } catch (Throwable t) {
            extentManager.logFailure("Waiting for an Element : " + locatorKey + " with timeout: " + timeout + " error message is :" + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }
}
