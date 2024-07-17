package com.app.tests;

import com.app.annotations.LazyAutowired;
import com.app.utils.date.DataTimeGenerator;
import com.app.utils.date.patterns.DataTimePattern;
import com.app.utils.extent_reports.manager.ExtentManager;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.lang.reflect.Method;
import java.util.Optional;

import static com.app.utils.date.DataTimeGenerator.*;
import static com.app.utils.date.patterns.DataTimePattern.*;

@SpringBootTest
public abstract class BaseTest {
    @Value("${log4j.config.path}")
    private String log4jConfigPath;
    @Autowired
    private Playwright playwright;
    @Autowired
    private Page page;
    @Autowired
    private Browser browser;

    @Autowired
    protected ExtentManager extentManager;
    protected static final Logger logger = Logger.getLogger(BaseTest.class);

    @AfterEach
    public void after() {
        extentManager.flushReports();
        page.close();
        browser.close();
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        extentManager.startTest(testInfo.getTestMethod().get().getName());
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
        logger.info("\n\n\n\n\n Tests execution starts => %s \n\n\n\n\n".formatted(getDateTime(HHMMSSDDMMYY)));
        logger.info("Test execution starts ! ! !");
    }




    // TODo 2 Stworzenie metod click, type, fill etc w base page z logowaniem itp. ??? -> Nikoniecznie możemy to ominąć i pracowac na page w pomie. do
    // TODO 3 dodanie informacji o browserze w pliku app properties i implementowanie ich podczas tworzenia beana
    // TODO 4 obejrzec odcinek o extent report => DO OBIADU !!!!

}
