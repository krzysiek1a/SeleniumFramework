package org.example;

import com.aventstack.extentreports.ExtentTest;
import org.example.pageObjects.FormPage;
import org.example.pageObjects.SubmitPage;
import org.example.testComponents.BaseTest;
import org.example.testComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void submitPageTest() {
        // Index page ==================================================================================================
        indexPage
                .goToIndexPage();

        // Form page ===================================================================================================
        FormPage formPage = indexPage
                .clickFormLink()
                .waitForFormPageToAppear()
                .sendKeysToTextInput("Hi how are you")
                .sendKeysToPasswordInput("Secret pass shhhhh")
                .selectDropdownByValue("2");

        // Submit Page =================================================================================================
        reportLog("You are on submit page and see ABC");
        SubmitPage submitPage = formPage
                .clickButtonAndGoToSubmitPage();
        Assert.assertEquals(submitPage.getSubmitMsg(), "ABC");
    }

    @Test
    public void errorPageTest() {
        // Index page ==================================================================================================
        indexPage
                .goToIndexPage();

        // Form page ===================================================================================================
        FormPage formPage = indexPage
                .clickFormLink()
                .waitForFormPageToAppear()
                .sendKeysToTextInput("Hi how are you")
                .sendKeysToPasswordInput("Secret pass shhhhh")
                .selectDropdownByValue("2");

        // Submit Page =================================================================================================
        SubmitPage submitPage = formPage
                .clickButtonAndGoToSubmitPage();
        Assert.assertEquals(submitPage.getSubmitMsg(), "Received!");
    }
}
