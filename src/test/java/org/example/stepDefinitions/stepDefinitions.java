package org.example.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageObjects.FormPage;
import org.example.pageObjects.IndexPage;
import org.example.pageObjects.SubmitPage;
import org.example.testComponents.BaseTest;
import org.testng.Assert;

public class stepDefinitions extends BaseTest {

    IndexPage indexPage;
    FormPage formPage;
    SubmitPage submitPage;

    @Given("I landed on index page")
    public void iLandedOnEcommercePage() {
        indexPage = lunchApplication();
        indexPage.goToIndexPage();
    }

    @Given("Go to form page")
    public void goToIndexPage() {
        formPage = indexPage.clickFormLink();
    }

    @When("^Fill the form with (.+) and (.+)$")
    public void fillTheForm(String name, String pass) {
        formPage
                .waitForFormPageToAppear()
                .sendKeysToTextInput(name)
                .sendKeysToPasswordInput(pass)
                .selectDropdownByValue("2");
    }

    @Then("Assert correct message on submit page {string}")
    public void assertCorrectMsg(String msg){
        submitPage = formPage
                .clickButtonAndGoToSubmitPage();
        Assert.assertEquals(submitPage.getSubmitMsg(), msg);
    }

    @AfterAll
    public void afterAll() {
        driver.close();
    }

}
