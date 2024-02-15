package org.example.testComponents;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.example.pageObjects.IndexPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public IndexPage indexPage;

    public WebDriver initializeDriver() {
        Properties properties = getProperties();

        String browserName = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : properties.getProperty("browser");
        String pageLoadTimeout = properties.getProperty("pageLoadTimeout");
        String windowsMaximize = properties.getProperty("windowsMaximize");
        String deleteAllCookies = properties.getProperty("deleteAllCookies");
        String waitTimeout = properties.getProperty("waitTimeout");

        if (browserName.equalsIgnoreCase("chrome")) {
             driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
             driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
             driver = new EdgeDriver();
        }

        if (deleteAllCookies.equalsIgnoreCase("true")) {
            driver.manage().deleteAllCookies();
        }
        if (windowsMaximize.equalsIgnoreCase("true")) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(waitTimeout)));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(pageLoadTimeout)));

        return driver;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/java/org/example/resources/GlobalData.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public void reportLog(String message) {
        Listeners.extentTest.get().log(Status.INFO, message);
    }

    @BeforeTest
    public IndexPage lunchApplication() {
        driver = initializeDriver();
        indexPage = new IndexPage(driver);
        return indexPage;
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }


}
