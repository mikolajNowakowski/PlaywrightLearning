package com.app.tests.handlers;

import com.app.annotations.LazyAutowired;
import com.app.utils.date.DataTimeGenerator;
import com.app.utils.date.patterns.DataTimePattern;
import com.app.utils.extent_reports.manager.ExtentManager;
import com.app.utils.popups.PopupsManager;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

import static com.app.utils.date.DataTimeGenerator.*;

@Component
public class ScreenshotExceptionHandler implements TestExecutionExceptionHandler {

    @Autowired
    private PopupsManager popupsManager;

    @LazyAutowired
    private ExtentManager extentManager;

    @Value("${screenshots.path}")
    private String screenShotsBasePath;


    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        var scPath = "%s %s %s.png".formatted(screenShotsBasePath,
                extensionContext
                        .getTags()
                        .stream()
                        .findFirst()
                        .orElseThrow(),
                getDateTime(DataTimePattern.HHMMSSDDMMYY));

        popupsManager.returnVisiblePage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(scPath)));
        extentManager.getTest().fail(throwable).addScreenCaptureFromPath(scPath);
    }
}
