package org.example.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporter {
    static ExtentReports extent;
    static final String reportAndScreensDate = getDate();
    private static final String path = System.getProperty("user.dir") + "\\reports\\" + reportAndScreensDate;
    public static ExtentReports getReportObject() {
//        String dateName = getDate();
        ExtentSparkReporter reporter = new ExtentSparkReporter(path + "\\ExtentReport_" + reportAndScreensDate + ".html");
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }

    public static String getScreenshot(String testCaseName, WebDriver driver) {
        String currentDateTime = getDate();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(path + "\\" + testCaseName + currentDateTime + ".png");
        try {
            FileUtils.copyFile(source, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "\\reports\\" + reportAndScreensDate + "\\" + testCaseName + currentDateTime + ".png";
    }

    private static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd'T'hh-mm-ss_SS").format(new Date());
    }
}
