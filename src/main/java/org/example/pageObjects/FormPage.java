package org.example.pageObjects;

import org.example.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AbstractComponent {
    WebDriver driver;
    public FormPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "my-text-id")
    WebElement textInput;
    @FindBy(xpath = "//input[@name='my-password']")
    WebElement passwordInput;
    @FindBy(xpath = "//select[@class='form-select']")
    WebElement dropdown;
    @FindBy(xpath = "//button")
    WebElement button;
    @FindBy(tagName = "h1")
    WebElement formPageTitle;

    public SubmitPage clickButtonAndGoToSubmitPage() {
        button.click();
        return new SubmitPage(driver);
    }

    public FormPage selectDropdownByValue(String value) {
        super.selectDropdownByValue(dropdown, value);
        return this;
    }

    public FormPage waitForFormPageToAppear() {
        waitForElementToAppear(10, formPageTitle);
        return this;
    }

    public FormPage sendKeysToTextInput(String text) {
        textInput.sendKeys(text);
        return this;
    }

    public FormPage sendKeysToPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

}
