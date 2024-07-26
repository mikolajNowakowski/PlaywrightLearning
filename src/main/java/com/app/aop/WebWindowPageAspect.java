package com.app.aop;


import com.app.annotations.LazyAutowired;
import com.app.annotations.WebWindow;
import com.app.pom.windows.WebWindowInterface;
import com.app.utils.popups.PopupsManager;
import com.microsoft.playwright.Page;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WebWindowPageAspect {

    @LazyAutowired
    private PopupsManager popupsManager;

    @Pointcut("@target(webWindow) && within(com.app..*)")
    public void webWindowAnnotatedMethod(WebWindow webWindow) {}

    @Before(value = "webWindowAnnotatedMethod(webWindow)", argNames = "joinPoint,webWindow")
    public void before(JoinPoint joinPoint, WebWindow webWindow) {
        Object target = joinPoint.getTarget();
        if (target instanceof WebWindowInterface) {
            WebWindowInterface window = (WebWindowInterface) target;
            window.setPageAsFront();
        }
    }

//    @After(value = "webWindowAnnotatedMethod(webWindow)", argNames = "webWindow")
//    public void after(WebWindow webWindow) {
//        Thread.sleep();
//        popupsManager.setFocusToMainPage();
//    }


}
