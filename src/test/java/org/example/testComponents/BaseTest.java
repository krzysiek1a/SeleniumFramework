package org.example.testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.example.pageObjects.IndexPage;
import org.example.resources.ExtentReport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public IndexPage indexPage;

    public WebDriver initializeDriver() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/java/org/example/resources/GlobalData.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
             driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
             driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
             driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        try {
            FileUtils.copyFile(source, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "\\reports\\" + testCaseName + ".png";
    }

    @BeforeTest
    public IndexPage lunchApplication() {
        driver = initializeDriver();
        indexPage = new IndexPage(driver);
        return indexPage;
    }

    public void reportLog(String message) {
        Listeners.extentTest.get().log(Status.INFO, message);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}
