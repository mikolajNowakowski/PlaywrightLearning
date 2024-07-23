package com.app.tests.base;

import com.app.annotations.LazyAutowired;
import com.app.tests.assertions.LocalAssertions;
import com.app.utils.faker.FakerManager;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.Optional;

import static com.app.utils.date.DataTimeGenerator.*;
import static com.app.utils.date.patterns.DataTimePattern.*;

@SpringBootTest
public abstract class BaseTest extends LocalAssertions {
    @Value("${log4j.config.path}")
    private String log4jConfigPath;
    @LazyAutowired
    private Page page;
    @LazyAutowired
    private Browser browser;
    @LazyAutowired
    protected FakerManager fakerManager;
    protected static final Logger log = Logger.getLogger(BaseTest.class);

    @AfterEach
    public void after() {
        extentManager.flushReports();
        page.close();
        browser.close();
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        extentManager.startTest("Tag name: %s, Method name: %s".formatted(testInfo.getTags().stream().findFirst().orElseThrow(),testInfo.getTestMethod().get().getName()));
    }

    @RegisterExtension
    AfterTestExecutionCallback afterTestExecutionCallback = new AfterTestExecutionCallback() {
        @Override
        public void afterTestExecution(ExtensionContext context) {
            Optional<Throwable> exception = context.getExecutionException();
            if (exception.isPresent()) {
                extentManager.logFailure("Test failed: " + exception.get().getMessage());
            } else {
                extentManager.logSuccess("Test passed successfully");
            }
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


    // TODO więcej metody w Base Page t dodania opcje bezarumentowe page i bez
    // TODO Wiecej asercji w local assertions + DODANIE SCREENSHOTÓW I LISTENERÓW!!!!!!!!!!1

}
