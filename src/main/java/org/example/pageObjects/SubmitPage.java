package org.example.pageObjects;

import org.example.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitPage extends AbstractComponent {
    WebDriver driver;
    public SubmitPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[@id='message']")
    WebElement formMsg;

    public String getSubmitMsg() {
        return waitForElementToAppear(10, formMsg).getText();
    }
}
