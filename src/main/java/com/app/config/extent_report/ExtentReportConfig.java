package com.app.config.extent_report;


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
    private String sparkOut;

    @Bean
    public ExtentReports extentReports() {
        ExtentReports extentReports = new ExtentReports();

        if (sparkStart) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(sparkOut);
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
