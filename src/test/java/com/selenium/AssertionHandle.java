package com.selenium;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class AssertionHandle {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeClass
    public void start(){
        playwright = Playwright.create();
        browserType = playwright.chromium();

        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized"))); // Maximize window

        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null)); // Use full screen size

        page = browserContext.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    @org.testng.annotations.Test
    public void loginFlow() throws InterruptedException {
        // Open URL
        page.navigate("https://testing-and-learning-hub.vercel.app/index.html");
        Thread.sleep(1000);

        // Enter Email
        ElementHandle elementUname = page.querySelector("#loginEmail");
        elementUname.fill("student@ittrainingbd.com");
        Thread.sleep(1000);

        // Enter Password
        ElementHandle elementPass = page.querySelector("#loginPassword");
        elementPass.fill("ittrainingbd");



        page.waitForSelector("#signInBtn", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("#signInBtn", new Page.ClickOptions().setForce(true));
        Thread.sleep(2000); // Wait for the next page to load



    }


    @Test(dependsOnMethods = "loginFlow")
    public void hardAssertion(){
        String assertTitle = page.title();
        String expectedTitle = "Testing And Learning Hub";
        Assert.assertEquals(assertTitle, expectedTitle);
        page.click("//a[normalize-space()='Web Automation']");
        System.out.println("assertTitle: " + assertTitle);

    }
    @Test(dependsOnMethods = "hardAssertion")
    public void softAssertion() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        String actualTitle = page.title();
        String expectedTitle = "Testing And Learning Hub";
        softAssert.assertEquals(actualTitle, expectedTitle);
        page.click("//a[normalize-space()='Web Automation']");
        Thread.sleep(2000);
        softAssert.assertAll();


    }
//    @org.testng.annotations.Test(dependsOnMethods = "loginFlow")
//    public void clickGetStarted() throws InterruptedException {
//        page.click("//a[@class='btn-get-started']");
//        Thread.sleep(2000);
//    }
//    @org.testng.annotations.Test(dependsOnMethods = "loginFlow")
//    public void clickGetStartedBasic() throws InterruptedException {
//        page.click("a[href='/WebAutomation/contents/basic.html']");
//        Thread.sleep(2000);
//    }
//
//
//    @org.testng.annotations.Test(dependsOnMethods = "clickGetStartedBasic") //scroll to specific location
//    public void scrollToSpecificLocationiFrame() throws InterruptedException {
//        ElementHandle element = page.querySelector("//a[normalize-space()='Go to JavaScript Alerts']");
//        element.scrollIntoViewIfNeeded();
//        Thread.sleep(2000);
//        System.out.println(element.textContent());
//        Thread.sleep(2000);
//        element.click();
//        Thread.sleep(2000);
//
//
//    }



    @AfterClass
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
