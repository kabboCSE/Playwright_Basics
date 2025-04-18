package com.selenium;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.util.Arrays;

public class AlertHandle {
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

    @Test
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
    public void clickGetStarted() throws InterruptedException {
        page.click("//a[@class='btn-get-started']");
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods = "loginFlow")
    public void clickGetStartedBasic() throws InterruptedException {
        page.click("a[href='/WebAutomation/contents/basic.html']");
        Thread.sleep(2000);
    }


    @Test(dependsOnMethods = "clickGetStartedBasic") //scroll to specific location
    public void scrollToSpecificLocationiFrame() throws InterruptedException {
        ElementHandle element = page.querySelector("//a[normalize-space()='Go to JavaScript Alerts']");
        element.scrollIntoViewIfNeeded();
        Thread.sleep(2000);
        System.out.println(element.textContent());
        Thread.sleep(2000);
        element.click();
        Thread.sleep(2000);


    }
    @Test(dependsOnMethods = "scrollToSpecificLocationiFrame")
    public void simpleAlert() throws InterruptedException {
        page.onceDialog(dialog -> {
            System.out.println(dialog.message());
            try {
                Thread.sleep(3000);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
            dialog.accept();
        } );

        page.click("//button[normalize-space()='Show Alert']");
        Thread.sleep(3000);
    }
    @Test(dependsOnMethods = "simpleAlert")
    public void confirmAlert() throws InterruptedException {
        page.onceDialog(dialog -> {
            System.out.println(dialog.type());
            System.out.println(dialog.message());
            try {
                Thread.sleep(3000);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
            dialog.dismiss();
            try {
                Thread.sleep(2000);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Cancel successful");


        } );

        page.click("//button[normalize-space()='Show Confirm']");
        Thread.sleep(3000);
    }
//@Test(dependsOnMethods = "scrollToSpecificLocationiFrame")
//public void simpleAlert() throws InterruptedException {
//    page.onceDialog(dialog -> {
//        System.out.println("Alert message: " + dialog.message());
//        try {
//            Thread.sleep(2000); // Pause to let the alert be visible
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        dialog.accept();
//    });
//
//    page.click("//button[normalize-space()='Show Alert']");
//    Thread.sleep(3000); // Wait to observe post-alert behavior
//}


    @AfterClass
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
