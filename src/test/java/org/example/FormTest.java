package org.example;

import com.aventstack.extentreports.ExtentTest;
import org.example.data.DataReader;
import org.example.pageObjects.FormPage;
import org.example.pageObjects.SubmitPage;
import org.example.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class FormTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void formDataPageTest(HashMap<String, String> input) {
        // Index page ==================================================================================================
        reportLog("Go to index page.");
        indexPage
                .goToIndexPage();

        // Form page ===================================================================================================
        reportLog("Go to form page and fill inputs: " + input.get("name") + " and " + input.get("password"));
        FormPage formPage = indexPage
                .clickFormLink()
                .waitForFormPageToAppear()
                .sendKeysToTextInput(input.get("name"))
                .sendKeysToPasswordInput(input.get("password"))
                .selectDropdownByValue("2");
        // Submit Page =================================================================================================
        reportLog("You are on submit page and see Received");
        SubmitPage submitPage = formPage
                .clickButtonAndGoToSubmitPage();
        Assert.assertEquals(submitPage.getSubmitMsg(), "Received!");
    }

    @Test
    public void productDetailsPageTest() {
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

    @DataProvider
    public Object[][] getData() {

        DataReader dataReader = new DataReader();
        List<HashMap<String, String>> jsonData = dataReader.getJsonDataToMap("src/test/java/org/example/data/loginData.json");
        Object[][] dataObject = new Object[jsonData.size()][1];
        for (int i = 0; i < jsonData.size(); i++) {
            dataObject[i][0] = jsonData.get(i);
        }
        return dataObject;
// ======================================================================
//        return new Object[][]{{"Robo", "pass1"}{"Max", "Pass2"}}
// ======================================================================
//        HashMap<String, String> map = new HashMap<>();
//        map.put("name", "Robo");
//        map.put("password", "Secret pass shhhhh");
//
//        HashMap<String, String> map1 = new HashMap<>();
//        map1.put("name", "Miro");
//        map1.put("password", "some password");

//        return new Object[][]{{map},{map1}};
    }
}
