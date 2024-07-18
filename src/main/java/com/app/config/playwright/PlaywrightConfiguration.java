package com.app.config.playwright;

import com.microsoft.playwright.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class PlaywrightConfiguration {

    @Value("${browser}")
    private String browserName;

    @Value("#{'${browser.options}'.split(',')}")
    private List<String> browserOptions;

    protected static final Logger log = Logger.getLogger(PlaywrightConfiguration.class);

    @Bean
    public Playwright playwright() {
        return Playwright.create();
    }

    @Bean
    @Scope("browserScope")
    public Browser browser(Playwright playwright){


        switch (browserName) {
            case "chrome" -> {
                log.info("Launching Chrome browser");
                return playwright.chromium()
                        .launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setArgs(browserOptions));
            }
            case "headless" -> {
                log.info("Launching Headless Mode");
                return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            }
            case "firefox" -> {
                log.info("Launching Firefox browser");
                return playwright.firefox()
                        .launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false).setArgs(browserOptions));
            }
            case "webkit" -> {
                log.info("Launching Webkit browser");
                return playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(browserOptions));
            }
            default -> throw new IllegalArgumentException();
        }
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
