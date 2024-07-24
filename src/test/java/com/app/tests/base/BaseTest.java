package com.app.tests.base;

import com.app.annotations.LazyAutowired;
import com.app.tests.assertions.LocalAssertions;
import com.app.tests.handlers.ScreenshotExceptionHandler;
import com.app.utils.date.patterns.DataTimePattern;
import com.app.utils.faker.FakerManager;
import com.app.utils.popups.PopupsManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

import static com.app.utils.date.DataTimeGenerator.*;
import static com.app.utils.date.patterns.DataTimePattern.*;

@SpringBootTest
public abstract class BaseTest extends LocalAssertions implements TestExecutionExceptionHandler {
    @Value("${log4j.config.path}")
    private String log4jConfigPath;
    @Value("${screenshots.path}")
    private String screenShotsBasePath;
    @LazyAutowired
    private Page page;
    @LazyAutowired
    private Browser browser;
    @LazyAutowired
    protected FakerManager fakerManager;
    @LazyAutowired
    protected PopupsManager popupsManager;
    protected static final Logger log = Logger.getLogger(BaseTest.class);

    @AfterEach
    public void after() {
//        var scPath = "%s_%s_%s.jpg".formatted(screenShotsBasePath,
//              "DUPA",
//                getDateTime(DataTimePattern.HHMMSSDDMMYY_TO_FILE_NAME));
//        Path screentShotPath = Paths.get(scPath);
//        popupsManager.returnVisiblePage().screenshot(new Page.ScreenshotOptions().setPath(screentShotPath));
//        extentManager.getTest().fail("TEST FAIL",
//                MediaEntityBuilder.createScreenCaptureFromPath(scPath).build());


        extentManager.flushReports();
        page.close();
        browser.close();
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        extentManager.startTest("Tag name: %s, Method name: %s".formatted(testInfo.getTags().stream().findFirst().orElseThrow(),testInfo.getTestMethod().get().getName()));
    }

    @RegisterExtension
    AfterTestExecutionCallback afterTestExecutionCallback = context -> {
        var scPath = "%s_%s_%s.png".formatted(screenShotsBasePath,
                context.getTags()
                        .stream()
                        .findFirst()
                        .orElseThrow(),
                getDateTime(DataTimePattern.HHMMSSDDMMYY_TO_FILE_NAME));


        Optional<Throwable> exception = context.getExecutionException();
        if (exception.isPresent()) {
            Path screentShotPath = Paths.get(scPath);
            popupsManager.returnVisiblePage().screenshot(new Page.ScreenshotOptions().setPath(screentShotPath));
            byte[] fileContent = Files.readAllBytes(screentShotPath);
            String base64Image = Base64.getEncoder().encodeToString(fileContent);

            if (Files.exists(screentShotPath) && Files.size(screentShotPath) > 0) {
                extentManager.getTest().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
                Thread.sleep(1000);

            } else {
                extentManager.getTest().fail("Screenshot capture failed").addScreenCaptureFromPath(scPath);
            }

        } else {
            extentManager.logSuccess("Test passed successfully");
        }
    };

    @BeforeTestExecution
    public void setUp() {
        PropertyConfigurator.configure(log4jConfigPath);
    }

    @BeforeAll
    public static void beforeAll() {
        log.info("\n\n\n\n\n Tests execution starts => %s \n\n\n\n\n".formatted(getDateTime(HHMMSSDDMMYY)));
        log.info("Test execution starts ! ! !");
    }





    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        var scPath = "%s %s %s.jpg".formatted(screenShotsBasePath,
                extensionContext
                        .getTags()
                        .stream()
                        .findFirst()
                        .orElseThrow(),
                getDateTime(DataTimePattern.HHMMSSDDMMYY));

        popupsManager.returnVisiblePage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(scPath)));
        extentManager.getTest().fail(throwable).addScreenCaptureFromPath(scPath);
    }


    // TODO więcej metody w Base Page t dodania opcje bezarumentowe page i bez
    // TODO Wiecej asercji w local assertions + DODANIE SCREENSHOTÓW I LISTENERÓW!!!!!!!!!!1



    // TODO POSTPRZATANIE BAJZLU ZE SCREENSHOTAMI !!! UZYCIE BASE 64 !!!!
}
