package com.app.aop;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.utils.popups.PopupsManager;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WebPageAspect {

    @LazyAutowired
    private PopupsManager popupsManager;

    @Before("@target(webPage) && within(com.app..*)")
    public void before(WebPage webPage) {
        popupsManager.setFocusToMainPage();
    }
}
