package com.selenium;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CheckboxHandling {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeSuite
    public void start(){
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browser.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    @Test
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/registration_form.html");
        Thread.sleep(5000);
    }

    //@Test(dependsOnMethods = "openUrl")
    public void clickOnCheckbox() throws InterruptedException {
        ElementHandle element = page.querySelector("//input[@id='hobby1']");
        element.click();
    }

    @Test(dependsOnMethods = "openUrl")
    public void clickMultipleCheckbox() throws InterruptedException {
        List<ElementHandle> element = page.querySelectorAll("//input[@type=\'checkbox\']");
        for (ElementHandle checkbox : element) {
            if (!checkbox.isChecked()) {
                checkbox.click();
                Thread.sleep(1000);
            }
        }

        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void clickMultipleUnCheckbox() throws InterruptedException {
        List<ElementHandle> element = page.querySelectorAll("//input[@type=\'checkbox\']");
        for (ElementHandle Uncheck : element) {
            if (Uncheck.isChecked()) {
                Uncheck.click();
                Thread.sleep(1000);
            }
        }

        Thread.sleep(2000);
    }




    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
