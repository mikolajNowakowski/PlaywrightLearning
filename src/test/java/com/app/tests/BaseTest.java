package com.app.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class BaseTest {
    @Autowired
    private Playwright playwright;
    @Autowired
    private Page page;
    @Autowired
    private Browser browser;

    @AfterEach
    public void after() {
        page.close();
        browser.close();
    }


}
