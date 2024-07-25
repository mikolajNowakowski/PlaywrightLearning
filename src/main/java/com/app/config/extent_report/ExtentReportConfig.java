package com.app.config.extent_report;


import com.app.utils.date.DataTimeGenerator;
import com.app.utils.date.patterns.DataTimePattern;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ExtentReportConfig {

    @Value("${extent.reporter.spark.start}")
    private boolean sparkStart;

    @Value("${extent.reporter.spark.config.file}")
    private String sparkConfigFile;

    @Value("${extent.reporter.spark.out}")
    private String sparkOutPath;

    @Bean
    public ExtentReports extentReports() {
        ExtentReports extentReports = new ExtentReports();

        var fileName = "%sextent-report_%s.html".formatted(sparkOutPath,DataTimeGenerator.getDateTime(DataTimePattern.HHMMSSDDMMYY_TO_FILE_NAME));

        if (sparkStart) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
            try {
                sparkReporter.loadXMLConfig(sparkConfigFile);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            extentReports.attachReporter(sparkReporter);
        }

        return extentReports;
    }
}
