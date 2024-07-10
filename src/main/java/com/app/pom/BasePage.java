package com.app.pom;


import com.app.annotations.LazyAutowired;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public abstract class BasePage {
    @LazyAutowired
    protected Browser browser;
    @LazyAutowired
    protected Page page;

}
