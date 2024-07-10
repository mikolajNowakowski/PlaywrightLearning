package com.app.config.playwright.scopes.page;

import com.app.config.playwright.scopes.browser.BrowserScopePostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageScopeConfig {

    @Bean(name = "pageScopeBeanFactoryPostProcessor")
    public static BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return new PageScopePostProcessor();
    }
}
