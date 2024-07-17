package com.app.pom;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;
import com.aventstack.extentreports.ExtentReports;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;


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
    protected ExtentReports extentReports;

    protected static final Logger logger = Logger.getLogger(BasePage.class);
    public void goToMainUrl(){
        logger.info("navigating to main url");

        page.navigate(mainUrl);
    }

    protected String mainBarWishListButton = "mainBarWishListButton";
    protected String mainBarShopButton = "mainBarShopButton";
    protected String mainBarMyAccountButton = "mainBarMyAccountButton";




}
