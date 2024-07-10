package com.app.config.playwright;

import com.microsoft.playwright.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class PlaywrightConfiguration {
    @Bean
    public Playwright playwright() {
        return Playwright.create();
    }

    @Bean
    @Scope("browserScope")
    public Browser browser(Playwright playwright){
       return playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(List.of("--start-maximized")).setHeadless(false));
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BrowserContext browserContext(Browser browser) {
        return browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
    }

    @Bean
    @Scope("pageScope")
    public Page page(BrowserContext browserContext){
        return browserContext.newPage();
    }
}
