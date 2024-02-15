package org.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
    WebDriver driver;
    public IndexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='web-form.html']")
    WebElement webFormLink;

    public FormPage clickFormLink() {
        new Actions(driver)
                .scrollToElement(webFormLink)
                .perform();
        webFormLink.click();
        return new FormPage(driver);
    }

    public IndexPage goToIndexPage() {
        driver.get("https://www.selenium.dev/selenium/web/index.html");
        return this;
    }
}
